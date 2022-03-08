package com.skypremiuminternational.app.app.features.profile.my_profile;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.TRANSPARENT;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.membership.MembershipStatementActivity;
import com.skypremiuminternational.app.app.features.my_favourite.MyFavouriteActivity;
import com.skypremiuminternational.app.app.features.my_orders.MyOrderActivity;
import com.skypremiuminternational.app.app.features.navigation.NavigationDialogFragment;
import com.skypremiuminternational.app.app.features.profile.booking.history.BookingsHistoryActivity;
import com.skypremiuminternational.app.app.features.profile.edit_profile.EditProfileActivity;
import com.skypremiuminternational.app.app.features.profile.invite_friend.InviteFriendActivity;
import com.skypremiuminternational.app.app.features.profile.manage_credit_card.ManageCreditCardActivity;
import com.skypremiuminternational.app.app.features.profile.manage_delivery_address.ManageDeliveryAddressActivity;
import com.skypremiuminternational.app.app.features.profile.my_reservation.MyResevationsActivity;
import com.skypremiuminternational.app.app.features.signin.SignInActivity;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.AESHexCryptUtil;
import com.skypremiuminternational.app.app.utils.DecimalUtil;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;
import com.skypremiuminternational.app.domain.utils.ProductUtil;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import dagger.android.AndroidInjection;
import timber.log.Timber;

public class ProfileActivity extends BaseActivity<ProfilePresenter> implements ProfileView<ProfilePresenter> {


  private static int SPEED_SWIPE = 20;

  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitle_toolbar;
  @BindView(R.id.tvNameProfile)
  TextView tvNameProfile;
  @BindView(R.id.tvMemberNumberProfile)
  TextView tvMemberNumberProfile;
  @BindView(R.id.layout_profile)
  LinearLayout layoutProfile;
  @BindView(R.id.layout_profile_card)
  ConstraintLayout layoutProfileCard;
  @BindView(R.id.tv_expire_date)
  TextView tvExpireDate;
  @BindView(R.id.txt_amtloyalty)
  TextView txtAmtLoyalty;
  @BindView(R.id.tvLoyaltyExpireDate)
  TextView tvLoyaltyExpireDate;
  @BindView(R.id.txt_amtloyaltyProfile)
  TextView txt_amtloyaltyProfile;
  @BindView(R.id.tvLoyaltyExpireDateProfile)
  TextView tvLoyaltyExpireDateProfile;
  @BindView(R.id.fl_profile_card)
  FrameLayout flProfileCard;
  @BindView(R.id.ll_member_card)
  RelativeLayout llMemberCard;
  @BindView(R.id.fl_top_card)
  FrameLayout flTopCard;
  @BindView(R.id.tvName)
  TextView tvName;
  @BindView(R.id.img_avatar)
  ImageView img_avatar;
  @BindView(R.id.ivQrCode)
  ImageView ivQrCode;


  private ProgressDialog progressDialog;

  /**
   * Date init
   */
  String salutation = "";
  String dateFormatInput = "yyyy-MM-dd";
  SimpleDateFormat dateF1 = new SimpleDateFormat("dd MMM yyyy");
  SimpleDateFormat dateF2 = new SimpleDateFormat("dd/MM/yyyy");
  DateFormat formater = new SimpleDateFormat(dateFormatInput);
  private String uploadImageUrl, imageName, imageType;



  /**
   * swipe card init
   */
  float dX;
  float dY;
  float llProfileY;
  int lastAction;

  /**
   * start zone
   */
  public static void startMe(Context context) {
    Intent intent = new Intent(context, ProfileActivity.class);
    context.startActivity(intent);
  }


  @Inject
  @Override
  public void injectPresenter(ProfilePresenter presenter) {
    super.injectPresenter(presenter);
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ButterKnife.bind(this);

    progressDialog = new ProgressDialog(this);
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);


    // set tile
    tvTitle_toolbar.setText(getResources().getString(R.string.profile));

    //init swipe card value
    llProfileY = layoutProfile.getY();

    setupCardSize();


    // get user detail form local
    presenter.getUserDetailFromLocal();

    GenerateClick();


  }
  public void GenerateClick(){
    try {
      //setting size of qr code
      int width =300,height = 300;
      int smallestDimension = width < height ? width : height;

      String sk = AESHexCryptUtil.getSk();
      String hash  = AESHexCryptUtil.encrypt("TranQuangToan",sk);

      String plaintext = AESHexCryptUtil.decrypt(hash,sk);
      String qrCodeData = "editText.getText().toString()";
      //setting parameters for qr code
      String charset = "UTF-8";
      Map<EncodeHintType, ErrorCorrectionLevel> hintMap =new HashMap<EncodeHintType, ErrorCorrectionLevel>();
      hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
      CreateQRCode(qrCodeData, charset, hintMap, smallestDimension, smallestDimension);

    } catch (Exception ex) {
      Log.e("QrGenerate",ex.getMessage());
    }
  }



  public  void CreateQRCode(String qrCodeData, String charset, Map hintMap, int qrCodeheight, int qrCodewidth){


    try {
      //generating qr code.
      BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
              BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
      //converting bitmatrix to bitmap

      int width = matrix.getWidth();
      int height = matrix.getHeight();
      int[] pixels = new int[width * height];
      // All are 0, or black, by default
      for (int y = 0; y < height; y++) {
        int offset = y * width;
        for (int x = 0; x < width; x++) {
          //for black and white
          //pixels[offset + x] = matrix.get(x, y) ? BLACK : WHITE;
          //for custom color
          pixels[offset + x] = matrix.get(x, y) ? BLACK : TRANSPARENT;
        }
      }
      //creating bitmap
      Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

      //getting the logo
      Bitmap overlay = BitmapFactory.decodeResource(getResources(), R.drawable.ic_qr_code);
      //setting bitmap to image view
      ivQrCode.setImageBitmap(mergeBitmaps(overlay,bitmap,matrix));

    }catch (Exception er){
      Log.e("QrGenerate",er.getMessage());
    }
  }

  public Bitmap mergeBitmaps(Bitmap overlay, Bitmap bitmap) {

    int height = bitmap.getHeight();
    int width = bitmap.getWidth();

    Bitmap combined = Bitmap.createBitmap(width, height, bitmap.getConfig());
    Canvas canvas = new Canvas(combined);
    int canvasWidth = canvas.getWidth();
    int canvasHeight = canvas.getHeight();

    canvas.drawBitmap(bitmap, new Matrix(), null);

    float height2 = overlay.getHeight();
    float width2 = overlay.getWidth();
    float ratio = height2 / width2;

    Bitmap bitmap1 = Bitmap.createScaledBitmap(overlay, 80, (int) (80*ratio), false);
    int centreX = (canvasWidth  - bitmap1.getWidth()) /2;
    int centreY = (canvasHeight - bitmap1.getHeight()) /2 ;
    canvas.drawBitmap(bitmap1, centreX, centreY, null);

    return combined;
  }
  public Bitmap mergeBitmaps(Bitmap overlay, Bitmap bitmap, BitMatrix matrix) {




    int height = bitmap.getHeight();
    int width = bitmap.getWidth();

    Bitmap combined = Bitmap.createBitmap(width, height, bitmap.getConfig());
    Canvas canvas = new Canvas(combined);
    int canvasWidth = canvas.getWidth();
    int canvasHeight = canvas.getHeight();
    int logoSize = (canvasWidth/3);


    float height2 = overlay.getHeight();
    float width2 = overlay.getWidth();
    float ratio = height2 / width2;

    Bitmap bitmap1 = Bitmap.createScaledBitmap(overlay, logoSize, (int) (logoSize*ratio), false);
    int centreX = (canvasWidth  - bitmap1.getWidth()) /2;
    int centreY = (canvasHeight - bitmap1.getHeight()) /2;

    int widthMatrix = matrix.getWidth();
    int heightMatrix = matrix.getHeight();
    int[] pixels = new int[widthMatrix * heightMatrix];
    // All are 0, or black, by default
    for (int y = 0; y < heightMatrix; y++) {
      int offset = y * widthMatrix;
      for (int x = 0; x < widthMatrix; x++) {
        //for black and white
        //pixels[offset + x] = matrix.get(x, y) ? BLACK : WHITE;
        //for custom color
        pixels[offset + x] = matrix.get(x, y) ? BLACK : TRANSPARENT;
        if(x>=centreX && x<=centreX+logoSize && y>=centreY && y<=centreY+(logoSize*ratio)){
          pixels[offset + x] = TRANSPARENT;
        }
      }
    }
    bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
    canvas.drawBitmap(bitmap, new Matrix(), null);
    canvas.drawBitmap(bitmap1, centreX, centreY, null);

    return combined;
  }

  private void setupCardSize() {
    ViewTreeObserver viewTreeObserver = flProfileCard.getViewTreeObserver();
    if (viewTreeObserver.isAlive()) {
      viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
          flProfileCard.getViewTreeObserver().removeOnGlobalLayoutListener(this);

          int wC = llMemberCard.getWidth();
          int hC = (int) (wC / 1.6);


          if (flTopCard.getHeight() < hC) {

            hC = flTopCard.getHeight() - 64;
            wC = (int) (hC * 1.6f);

          }
          FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(
              wC,
              hC
          );
          p.gravity = Gravity.BOTTOM | Gravity.CENTER;
          llMemberCard.setLayoutParams(p);


        }
      });
    }
  }

  @OnClick(R.id.ivNavigation_toolbar)
  void openMenu() {
    NavigationDialogFragment.newInstance().show(getSupportFragmentManager(), "Navigation");
  }

  @OnClick(R.id.tvMyFavourites)
  void onClickMyFavourite(){
    MyFavouriteActivity.startMe(this);
  }

  @OnClick(R.id.tvEditProfile)
  void onClickEditProfile(){
    EditProfileActivity.startMe(this);
  }

  @OnClick(R.id.tvMyMembershipStatement)
  void onClickMembership(){
    MembershipStatementActivity.startMe(this);
  }

  @OnClick(R.id.tvMyOrders)
  void onClickMyOrder(){
    MyOrderActivity.startMe(this);
  }

  @OnClick(R.id.tvMyBooking)
  void onClickMyBooking(){
    BookingsHistoryActivity.start(ProfileActivity.this);
  }

  @OnClick(R.id.tvMyReservation)
  void onClickMyReservation(){
    MyResevationsActivity.startMe(this);
  }

  @OnClick(R.id.tvManageDeliveryAddress)
  void onClickManageDeliveryAddress(){
    ManageDeliveryAddressActivity.startMe(this);
  }

  @OnClick(R.id.tvManageCreditCards)
  void onClickManageCreditCards(){
    ManageCreditCardActivity.startMe(this);
  }

  @OnClick(R.id.tvLogOut)
  void onClickLogOut(){
    presenter.onLogout();
    finish();
    Intent mIntent = new Intent(ProfileActivity.this, SignInActivity.class);
    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(mIntent);
  }


  @OnClick(R.id.tvInviteFriend)
  void onClickInviteFriend() {
      presenter.getReferralCodeAndDescription();
  }





  @Override
  public void renderUserDetail(UserDetailResponse userDetailResponse) {

    // repare data
    String memberExpiryDate = userDetailResponse.getExpiryDate();
    String loyaltyPointExpiryDate = userDetailResponse.getLoyaltyPointExpiryDate();
    salutation = userDetailResponse.getSalutation();


    String name = "";
    name += salutation;

    if (userDetailResponse.getFirstname() != null) {
      name += " " + userDetailResponse.getFirstname();
    }

    if (userDetailResponse.getLastname() != null) {
      name += " " + userDetailResponse.getLastname();
    }
    tvNameProfile.setText(name);
    tvName.setText(name);
    tvMemberNumberProfile.setText(userDetailResponse.getMemberNumber());

    /**
     *    ** show Member Exp Date
     */
    if (ProductUtil.isValid(memberExpiryDate)) {
      Date dateExpProfile = null;
      try {
        dateExpProfile = (Date) formater.parse(memberExpiryDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      String stringProfileDate = dateF1.format(dateExpProfile);
      tvExpireDate.setText(stringProfileDate);
    }

    /*
      Avatar
    */
    Timber.i("Avatar" + userDetailResponse.getAvatar());
    if (userDetailResponse.getAvatar() != null) {
      RequestOptions requestOptions = new RequestOptions();
      requestOptions.placeholder(R.drawable.ic_person_white_24);
      requestOptions.dontAnimate();
      requestOptions.centerCrop();
      requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
      requestOptions.error(R.drawable.ic_person_white_24);
      Glide.with(getApplicationContext())
          .asBitmap()
          .load(userDetailResponse.getAvatar())
          .apply(requestOptions)
          .into(new BitmapImageViewTarget(img_avatar) {
            @Override
            protected void setResource(Bitmap resource) {
              RoundedBitmapDrawable circularBitmapDrawable =
                  RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(),
                      resource);
              circularBitmapDrawable.setCircular(true);
              img_avatar.setImageDrawable(circularBitmapDrawable);
            }
          });
    }

    /**
     *    ** show Loyalty Point Number
     */
    if (userDetailResponse.getLoyaltyPoints() != null) {
      txtAmtLoyalty.setText(
          DecimalUtil.roundTowDecimalWithoutBrace(userDetailResponse.getLoyaltyPoints()));
      txt_amtloyaltyProfile.setText(
          DecimalUtil.roundTowDecimalWithoutBrace(userDetailResponse.getLoyaltyPoints()));
    } else {
      txtAmtLoyalty.setText(DecimalUtil.roundTwoDecimals(0.0));
      txt_amtloyaltyProfile.setText(DecimalUtil.roundTwoDecimals(0.0));
    }

    /**
     *    ** show Loyalty Point Exp
     */
    if (ProductUtil.isValid(loyaltyPointExpiryDate)) {

      Date dateExpPoint = null;
      try {
        dateExpPoint = (Date) formater.parse(loyaltyPointExpiryDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      String stringPointDate = dateF1.format(dateExpPoint);

      tvLoyaltyExpireDate.setText("(" + getString(R.string.sky_dollar_exp_on) + " " + stringPointDate + ")");
      tvLoyaltyExpireDateProfile.setText("(" + "Expiring on" + " " + dateF2.format(dateExpPoint) + ")");

    } else {
      tvLoyaltyExpireDate.setText("");
      tvLoyaltyExpireDateProfile.setText("");
    }

  }

  @Override
  public void goToInviteFriend(String code, String salutation, String firstname, String lastName, String description, String imgBannerUrl, String url) {
    InviteFriendActivity.startMe(this, code, salutation, firstname, lastName, description, imgBannerUrl, url);
  }

  @OnClick(R.id.layout_avatar)
  void onClickAvatar() {
    startCropImage(null);
  }

  private void startCropImage(Uri uri) {
    CropImage.activity(uri)
        //.setMinCropResultSize(500, 500)
        .setAspectRatio(500, 500)
        .setGuidelines(CropImageView.Guidelines.ON)
        .setMultiTouchEnabled(true)
        .start(this);
  }




  @OnTouch(R.id.layout_swipe_card_down)
  public boolean onTouchDown(View view, MotionEvent event) {
    switch (event.getActionMasked()) {
      case MotionEvent.ACTION_DOWN:
        dX = layoutProfile.getX() - event.getRawX();
        dY = layoutProfile.getY() - event.getRawY();
        lastAction = MotionEvent.ACTION_DOWN;
        break;

      case MotionEvent.ACTION_MOVE:


        ddDownY = (int) (yy - event.getRawY());
        Log.d("ACTION_DOWN", "ddDownY" + ddDownY);

        if (llProfileY <= event.getRawY() + dY) {
          layoutProfile.setY(event.getRawY() + dY);
          layoutProfileCard.setY(event.getRawY() + dY - layoutProfileCard.getHeight());
        } else {
          layoutProfile.setY(llProfileY);
        }
        yy = (int) event.getRawY();

        lastAction = MotionEvent.ACTION_MOVE;

        break;

      case MotionEvent.ACTION_UP:

        if (lastAction == MotionEvent.ACTION_DOWN || lastAction == MotionEvent.ACTION_MOVE) {
          if (ddDownY < -30) {
            autoMoveDown();
          } else if (layoutProfile.getY() <= llProfileY + (layoutProfile.getHeight() / 2)) {
            autoMoveUp();
          } else {
            autoMoveDown();
          }
          break;
        }


      default:
        return false;
    }
    return true;
  }


  int ddUpY = 0;
  int ddDownY = 0;
  int yy = 0;

  @OnTouch(R.id.layout_swipe_card_up)
  public boolean onTouchUp(View view, MotionEvent event) {
    switch (event.getActionMasked()) {
      case MotionEvent.ACTION_DOWN:
        dX = layoutProfile.getX() - event.getRawX();
        dY = layoutProfile.getY() - event.getRawY();
        lastAction = MotionEvent.ACTION_DOWN;
        break;

      case MotionEvent.ACTION_MOVE:
        layoutProfile.setVisibility(View.VISIBLE);
        layoutProfile.setAlpha(1);

        ddUpY = (int) (yy - event.getRawY());

        if (llProfileY <= event.getRawY() + dY) {
          layoutProfile.setY(event.getRawY() + dY);
          layoutProfileCard.setY(event.getRawY() + dY - layoutProfileCard.getHeight());

        } else {
          layoutProfile.setY(llProfileY);
        }
        yy = (int) event.getRawY();
        lastAction = MotionEvent.ACTION_MOVE;
        break;

      case MotionEvent.ACTION_UP:
        if (lastAction == MotionEvent.ACTION_DOWN || lastAction == MotionEvent.ACTION_MOVE) {
          if (layoutProfile.getY() <= (layoutProfile.getHeight() / 2)) {
            autoMoveUp();
          } else if (ddUpY > 30) {
            autoMoveUp();
          } else {
            autoMoveDown();
          }
          break;
        }


      default:
        return false;
    }
    return true;
  }

  void autoMoveUp() {
    ScheduledExecutorService executor =
        Executors.newSingleThreadScheduledExecutor();
    Runnable periodicTask = new Runnable() {
      public void run() {
        layoutProfile.setY(layoutProfile.getY() - SPEED_SWIPE);
        layoutProfileCard.setY(layoutProfileCard.getY() - SPEED_SWIPE);
        if (layoutProfile.getY() <= llProfileY) {
          layoutProfile.setY(llProfileY);
          layoutProfileCard.setY(llProfileY - +layoutProfile.getHeight());
          executor.shutdown();
        }

      }
    };
    try {
      executor.scheduleAtFixedRate(periodicTask, 1, layoutProfile.getHeight(), TimeUnit.MICROSECONDS);
    } catch (Exception e) {
      layoutProfile.setVisibility(View.VISIBLE);
      layoutProfile.setY(llProfileY);
    }
  }

  void autoMoveDown() {
    ScheduledExecutorService executor =
        Executors.newSingleThreadScheduledExecutor();
    Runnable periodicTask = new Runnable() {
      public void run() {
        layoutProfile.setY(layoutProfile.getY() + SPEED_SWIPE);
        layoutProfileCard.setY(layoutProfileCard.getY() + SPEED_SWIPE);
        if (layoutProfile.getY() >= layoutProfile.getHeight()) {
          layoutProfile.setY(llProfileY + layoutProfile.getHeight());
          layoutProfileCard.setY(llProfileY);
          executor.shutdown();
        }

      }
    };
    try {
      executor.scheduleAtFixedRate(periodicTask, 1, layoutProfile.getHeight(), TimeUnit.MICROSECONDS);
    } catch (Exception e) {
      layoutProfile.setVisibility(View.VISIBLE);
      layoutProfile.setY(llProfileY);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // handle result of CropImage
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
      CropImage.ActivityResult result = CropImage.getActivityResult(data);
      if (resultCode == RESULT_OK) {
        //image type
        if (result.getOriginalUri().getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
          ContentResolver cr = getApplicationContext().getContentResolver();
          imageType = cr.getType(result.getOriginalUri());
        } else {
          String extension =
              MimeTypeMap.getFileExtensionFromUrl(result.getOriginalUri().toString());
          if (extension != null) {
            imageType =
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension).toLowerCase();
          }
        }

        //file Name
        imageName = result.getUri().getLastPathSegment();

        final InputStream imageStream;
        try {
          imageStream = getContentResolver().openInputStream(result.getUri());
          final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
          byte[] b = baos.toByteArray();
          uploadImageUrl = Base64.encodeToString(b, Base64.DEFAULT);

          presenter.uploadImageToServer(uploadImageUrl, imageType, imageName);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
        Toast.makeText(this, "Upload Fail", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  protected void onResume() {
    presenter.getUserDetailFromLocal();
    super.onResume();
  }

  @Override
  public void showLoading() {
    if (isDestroyed()) return;
    progressDialog.show();
  }

  @Override
  public void hideLoading() {
    if (isDestroyed()) return;
    progressDialog.dismiss();
  }
  @Override
  public void showError(Throwable throwable) {
    Toast.makeText(this, "" + throwable, Toast.LENGTH_SHORT).show();
  }
}
