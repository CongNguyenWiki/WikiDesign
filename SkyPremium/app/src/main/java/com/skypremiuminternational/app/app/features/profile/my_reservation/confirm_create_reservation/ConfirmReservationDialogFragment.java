package com.skypremiuminternational.app.app.features.profile.my_reservation.confirm_create_reservation;

import static com.skypremiuminternational.app.app.features.profile.my_reservation.confirm_create_reservation.ReservationConfirmedDialogFragment.RESERVATION_CONFIRMED_DIALOG;
import static com.skypremiuminternational.app.app.utils.Constants.PATTERN_TIME_SHORT;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.profile.my_reservation.make_reservation.MakeAReservationDialogFragment;
import com.skypremiuminternational.app.app.internal.mvp.BaseDialogFragment;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.DateParser;
import com.skypremiuminternational.app.app.utils.ErrorMessageFactory;
import com.skypremiuminternational.app.data.network.request.ReservationRequest;
import com.skypremiuminternational.app.domain.models.reservation.CancelReservationResponse;
import com.skypremiuminternational.app.domain.models.reservation.ReservationResultResponse;

import java.text.ParseException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class ConfirmReservationDialogFragment extends BaseDialogFragment<ConfirmReservationPresenter>
    implements ConfirmReservationView<ConfirmReservationPresenter> {


  public static final String CONFIRM_DIALOG = "CONFIRM_DIALOG";

  public static final int ACTION_NEW = 0;
  public static final int ACTION_EDIT = 1;
  public static final int ACTION_BOOK_AGAIN = 2;
  public static final int ACTION_CANCEL = 3;

  @BindView(R.id.tv_confirm_question)
  TextView tvConfirmQuestion;
  @BindView(R.id.tv_confirm)
  TextView tvConfirm;
  @BindView(R.id.title)
  TextView title;
  @BindView(R.id.tv_question_1)
  TextView tvQuestionTop;
  @BindView(R.id.tv_cancel_warning)
  TextView tvCancelWarning;

  ProgressDialog progressDialog;

  ReservationRequest request;
  String restaurantName;
  MakeAReservationDialogFragment rootFragment;
  ConfirmListener listener;
  int action = 0;

  @Inject
  ErrorMessageFactory errorMessageFactory;

  public static ConfirmReservationDialogFragment newInstance(ReservationRequest request,
                                                             String restaurantName,
                                                             MakeAReservationDialogFragment rootFragment,
                                                             int action,
                                                             ConfirmListener listener){
    ConfirmReservationDialogFragment fragment = new ConfirmReservationDialogFragment();
    fragment.request = request;
    fragment.restaurantName = restaurantName;
    fragment.rootFragment = rootFragment;
    fragment.action  = action;
    fragment.listener  = listener;
    return fragment;
  }

  @Inject
  @Override
  public void injectPresenter(ConfirmReservationPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(view);

    progressDialog = new ProgressDialog(getActivity());
    progressDialog.setCancelable(false);
    tvConfirmQuestion.setText(createContentQuestion());
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_confirm_reservation;
  }

  String createContentQuestion() {
    String question = "";
    String sDate = "";
    try {
      sDate = DateParser.changeFormatDate(Constants.PATTERN_DATE_SHORT,Constants.PATTERN_DATE_FULL_SP_REV,request.getDate());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    String timeSlot = request.getTime();
    String hour =  timeSlot.substring(0,2);
    String min  = timeSlot.substring(2);

    String reserveTime = hour+"."+min;

    try {
      reserveTime = DateParser.changeFormatDate(Constants.PATTERN_TIME,PATTERN_TIME_SHORT,reserveTime);
      String marker = reserveTime.substring(4);
      reserveTime = reserveTime.substring(0,4);
      marker =  marker.toLowerCase();
      reserveTime = reserveTime+marker;

    } catch (ParseException e) {
      e.printStackTrace();
    }

    question =
        request.getAdult()
            + " " + res(R.string.q_re_1)
            + " " + request.getChild()
            + " " + res(R.string.q_re_2) + "\n"
            + " " + sDate
            + " " + res(R.string.at)
            + " " + reserveTime + "\n"
            + " " + res(R.string.at)
            + " " + request.getRestaurantName() + (action ==  ACTION_CANCEL ? "" : "?");
    if(action==ACTION_CANCEL){
      title.setText(R.string.title_cancel_question);
      tvQuestionTop.setText(R.string.reserve_cancel_question);
      tvCancelWarning.setText(Html.fromHtml("<b>"+getResources().getString(R.string.cancellation_policy)+"</b> "+ request.getCancelPolicy()));
      tvCancelWarning.setVisibility(View.VISIBLE);
      tvConfirm.setText(R.string.cancel_this_resercation);

    }else {
      tvCancelWarning.setVisibility(View.INVISIBLE);
    }

    return question;
  }

  String res(int res){
    return getResources().getString(res);
  }

  @OnClick(R.id.tv_confirm)
  void onClickConfirm(){
    logInformationReservation(request);
    if(action==ACTION_NEW){
      presenter.sendCreateReservation(request);
    }else if(action == ACTION_EDIT){
      if(!TextUtils.isEmpty(request.getReservationId()) && !TextUtils.isEmpty(request.getVerificationKey())){
        presenter.sendEditReservation(request, request.getReservationId(), request.getVerificationKey());
      } else{
        Toast.makeText(getActivity(),"Verification Key invalid",Toast.LENGTH_SHORT).show();
      }
    }else if(action==ACTION_BOOK_AGAIN){
      presenter.sendCreateReservation(request);
    }else if(action ==  ACTION_CANCEL){
      presenter.cancelReservation(request.getReservationId(),request.getVerificationKey());
    }

  }

  private void logInformationReservation(ReservationRequest request) {
    Log.d("RESERVATION_LOG_CONFIRM" ,
        "\noutlet: "                 +request.getOutletId()
            +"\nselectedDate:  "          +request.getDate()
            +"\ntvsAdultsNumber:  "       +request.getAdult()
            +"\ntvsChildrenNumber:  "     +request.getChild()
            +"\ntvsSpecialInstruction:  " +request.getNote()
            +"\nreserveTimeSelected: "    +request.getTime()
            +"\nproductId: "    +request.getProductId());
  }
  @OnClick(R.id.img_close)
  void onClickClose(){
    dismiss();
  }

  @Override
  public void renderCreateSuccess(ReservationResultResponse response) {
    if(response.getCode().equalsIgnoreCase("200")){
      ReservationConfirmedDialogFragment.newInstance(response,request).show(getActivity().getSupportFragmentManager(),RESERVATION_CONFIRMED_DIALOG);
      if(listener!=null){
        listener.onReloadHistoryDetail();
      }

      rootFragment.dismiss();
      dismiss();
    }else {
      new AlertDialog.Builder(getActivity())
          .setMessage(response.getMessage()).show();
      return;
    }
  }

  @Override
  public void renderCreateFailed() {

  }

  @Override
  public void showLoading() {
    if(!progressDialog.isShowing()){
      progressDialog.setMessage(res(R.string.loading));
      progressDialog.show();
    }
  }

  @Override
  public void hideLoading() {
    if(progressDialog!=null&&progressDialog.isShowing()){
      progressDialog.dismiss();
    }
  }

  public interface ConfirmListener{
    void onReloadHistoryDetail();
  }

  @Override
  public void renderCancel(CancelReservationResponse response) {
    if(response.getCode().equalsIgnoreCase("200")){
      listener.onReloadHistoryDetail();
      dismiss();
    }else {
      new AlertDialog.Builder(getActivity())
          .setMessage(response.getMessage()).show();
    }
  }

  @Override
  public void renderCancelError(String msg) {
    new AlertDialog.Builder(getActivity())
        .setMessage(msg).show();
  }
}
