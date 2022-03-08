package com.skypremiuminternational.app.app.features.profile.manage_credit_card;



import static com.skypremiuminternational.app.app.features.checkout.AddOrEditCreditCardDialog.ADD_CARD;
import static com.skypremiuminternational.app.app.features.checkout.AddOrEditCreditCardDialog.EDIT_CARD;
import static com.skypremiuminternational.app.app.features.checkout.AddOrEditCreditCardDialog.EXTRA_KEY;
import static com.skypremiuminternational.app.app.features.checkout.AddOrEditCreditCardDialog.FORM_DATA_KEY;
import static com.skypremiuminternational.app.app.features.checkout.AddOrEditCreditCardDialog.KEY_DIALOG_TYPE;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.checkout.AddOrEditCreditCardDialog;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.ErrorMessageFactory;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;
import com.skypremiuminternational.app.domain.models.creditCard.GetFormDataCreditCardResponse;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class ManageCreditCardActivity extends BaseActivity<ManageCreditCardPresenter>
    implements ManageCreditCardView<ManageCreditCardPresenter> {

  private static final String TAG_CREDIT_CARD_DIALOG = "CreditCardDialog";


  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitle_toolbar;
  @BindView(R.id.rvCreditCards)
  RecyclerView rv;
  @BindView(R.id.layout_no_credit)
  RelativeLayout layoutNoCreditCards;
  @BindView(R.id.img_add)
  ImageView imgAdd;
  @Inject
  ErrorMessageFactory errorMessageFactory;

  private CreditCardAdapter adapter;
  private ProgressDialog progressDialog;

  public static void startMe(Context context) {
    Intent intent = new Intent(context, ManageCreditCardActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_manage_credit_cards);
    ButterKnife.bind(this);
    tvTitle_toolbar.setText(getResources().getString(R.string.manage_credit_cards));
    setCreditCards();

    progressDialog = new ProgressDialog(this);
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);

    presenter.getListCreditCards();

  }

  @Inject
  @Override
  public void injectPresenter(ManageCreditCardPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @OnClick(R.id.ivNavigation_toolbar)
  void onClickMenu() {
    finish();
  }

  void setCreditCards() {
    rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    adapter = new CreditCardAdapter();
    adapter.setItemEditClickListener(item -> {
      presenter.getListFormDataCreditCards(item);
    });
    rv.setAdapter(adapter);
  }

  @OnClick(R.id.img_add)
  public void onClickAdd() {
    presenter.getListFormDataCreditCards(null);
  }

  @Override
  public void render(List<CardItem> response) {
    if (response.size() > 0) {
      rv.setVisibility(View.VISIBLE);
      layoutNoCreditCards.setVisibility(View.GONE);
      adapter.setDataList(response);
    } else {
      rv.setVisibility(View.GONE);
      layoutNoCreditCards.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void renderFormData(CardItem cardItem, GetFormDataCreditCardResponse response) {
    if (response != null){

      if (cardItem == null){
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DIALOG_TYPE, ADD_CARD);
        bundle.putSerializable(EXTRA_KEY, null);
        bundle.putSerializable(FORM_DATA_KEY,response);
        AddOrEditCreditCardDialog addOrEditCreditCardDialog = new AddOrEditCreditCardDialog();
        addOrEditCreditCardDialog.setArguments(bundle);
        addOrEditCreditCardDialog.setActionListener(new AddOrEditCreditCardDialog.ActionListener() {
          @Override
          public void onSaveClicked(CardItem creditCard) {
            if (creditCard != null) {
              presenter.addCreditCards(creditCard);
            }
          }

          @Override
          public void onDeleteClicked(CardItem creditCard) {
          }
        });
        addOrEditCreditCardDialog.show(getSupportFragmentManager(),
            TAG_CREDIT_CARD_DIALOG);
      }
      else {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DIALOG_TYPE, EDIT_CARD);
        bundle.putSerializable(EXTRA_KEY, cardItem);
        bundle.putSerializable(FORM_DATA_KEY,response);
        AddOrEditCreditCardDialog addOrEditCreditCardDialog = new AddOrEditCreditCardDialog();
        addOrEditCreditCardDialog.setArguments(bundle);
        addOrEditCreditCardDialog.setActionListener(new AddOrEditCreditCardDialog.ActionListener() {
          @Override
          public void onSaveClicked(CardItem creditCard) {
            if (creditCard != null) {
              presenter.updateCreditCard(creditCard);
            }
          }

          @Override
          public void onDeleteClicked(CardItem creditCard) {
            presenter.deleteCreditCards(creditCard.getId());
          }
        });
        addOrEditCreditCardDialog.show(getSupportFragmentManager(),
            TAG_CREDIT_CARD_DIALOG);
      }

    }

  }

  @Override
  public void hideAddOrUpdateCreditCardDialog() {
    AddOrEditCreditCardDialog fragmentCreditCard =
        (AddOrEditCreditCardDialog) getSupportFragmentManager().findFragmentByTag(
            TAG_CREDIT_CARD_DIALOG);
    if (fragmentCreditCard != null && fragmentCreditCard.getDialog().isShowing()) {
      fragmentCreditCard.dismiss();
    }
  }

  @Override
  public void showLoading(String message) {
    if (!isDestroyed()) {
      progressDialog.setMessage(message);
      progressDialog.show();
    }
  }

  @Override
  public void hideLoading() {
    if (!this.isDestroyed()) {
      progressDialog.dismiss();
    }
  }

  @Override
  public void render(Throwable error) {
    if (isDestroyed()) return;

    new AlertDialog.Builder(this)
        .setMessage(errorMessageFactory.getErrorMessage(error))
        .setPositiveButton(R.string.btn_label_ok, (dialog, which) -> dialog.dismiss()).setCancelable(false)
        .show();
  }







}
