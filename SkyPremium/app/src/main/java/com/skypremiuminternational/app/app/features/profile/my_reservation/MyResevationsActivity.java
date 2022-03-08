package com.skypremiuminternational.app.app.features.profile.my_reservation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.app.features.profile.my_reservation.detail.DetailsResevationActivity;
import com.skypremiuminternational.app.app.features.profile.my_reservation.detail_cheftable_reservation.DetailsReservationChefTableActivity;
import com.skypremiuminternational.app.app.features.profile.my_reservation.detail_toptable_reservation.DetailsReservationTopTableActivity;
import com.skypremiuminternational.app.app.internal.mvp.BaseActivity;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryRespone;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MyResevationsActivity extends BaseActivity<MyReservationsPresenter>
    implements MyReservationsView<MyReservationsPresenter> {

  @BindView(R.id.rv_reservation)
  RecyclerView rvReservation;
  @BindView(R.id.tvTitle_toolbar)
  TextView tvTitleToolbar;
  @BindView(R.id.tvCategory_filter)
  TextView tvCategoryFilter;
  @BindView(R.id.tvSort_filter)
  TextView tvSortFilter;
  @BindView(R.id.tv_num)
  TextView tvNum;
  @BindView(R.id.ll_no_reservation)
  LinearLayout llNoReservation;

  ProgressDialog progressDialog;
  MyReservationApdapter adapter;

//  int refineSelectedPos = 2;
  int refineSelectedPos = 0;
//  int sortSelectedPos = 0;
  int sortSelectedPos = 2;



  public static void startMe(Context context) {
    Intent intent = new Intent(context, MyResevationsActivity.class);
    context.startActivity(intent);
  }


  @Inject
  @Override
  public void injectPresenter(MyReservationsPresenter presenter) {
    super.injectPresenter(presenter);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_reservations);
    ButterKnife.bind(this);

    setRecyclerView();
    progressDialog = new ProgressDialog(this);
    progressDialog.setCancelable(false);



    initRefineSort();

  }

  private void initRefineSort() {

    tvCategoryFilter.setText("REFINE: "+Constants.refineArrReservation[refineSelectedPos]);
    tvSortFilter.setText("SORT BY: "+Constants.sortArrReservation[sortSelectedPos]);

  }

  @Override
  protected void onResume() {
    super.onResume();
    loadHistoryList();
  }

  private void loadHistoryList(){
    if(refineSelectedPos==0){
      presenter.getHistoryAll(Constants.sortDirectionReservation[sortSelectedPos]);
    }else {
      presenter.getHistoryFilter(Constants.sortDirectionReservation[sortSelectedPos],
          Constants.refineDirectionReservation[refineSelectedPos]
          );
    }
  }

  private void setRecyclerView() {
    adapter = new MyReservationApdapter();
    rvReservation.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    rvReservation.setAdapter(adapter);




    adapter.setActionListener(new MyReservationApdapter.ActionListener() {
      @Override
      public void onItemClicked(String bookingId, String typeReservation) {

        if (typeReservation != null && typeReservation.equalsIgnoreCase("top_table")){
          DetailsReservationTopTableActivity.startMe(MyResevationsActivity.this,bookingId);
        }
        else if (typeReservation != null && typeReservation.equalsIgnoreCase("chef_table")){
          DetailsReservationChefTableActivity.startMe(MyResevationsActivity.this,bookingId);
        }
        else {
          presenter.getBookingDetail(bookingId);
        }

      }
    });


  }

  @Override
  public void renderError(String msg) {
    new AlertDialog.Builder(this).setMessage(msg).show();
  }

  @Override
  public void renderHistoryReservation(ReserveHistoryRespone reserveHistoryRespone) {

    if(reserveHistoryRespone.getReserveHistoryItemList()!=null && reserveHistoryRespone.getReserveHistoryItemList().size()>0){
      rvReservation.setVisibility(View.VISIBLE);
      adapter.setData(reserveHistoryRespone.getReserveHistoryItemList());
      tvNum.setText(String.format("(%d)",reserveHistoryRespone.getReserveHistoryItemList().size()));
      llNoReservation.setVisibility(View.GONE);
    }else {
      tvNum.setText(String.format("(%d)",0));
      //tvNum.setText(String.format("(%d)",0));
      rvReservation.setVisibility(View.GONE);
      llNoReservation.setVisibility(View.VISIBLE);
    }
    tvTitleToolbar.setText("My Reservations");


  }


  @Override
  public void renderGotoBookingDetail(ReserveHistoryItem reserveHistoryItem) {

    DetailsResevationActivity.startMe(this, reserveHistoryItem);

  }



  @Override
  public void showLoading() {
    if(progressDialog!=null && !progressDialog.isShowing()){
      progressDialog.setMessage(getResources().getString(R.string.loading));
      progressDialog.show();
    }
  }


  @Override
  public void hideLoading() {
    if(progressDialog != null && progressDialog.isShowing()){
      progressDialog.dismiss();
    }
  }


  @OnClick(R.id.ivNavigation_toolbar)
  void onClickBack(){
    finish();
  }


  @OnClick(R.id.tvCategory_filter)
  void onClickCategory(){
    new AlertDialog.Builder(this).setTitle("REFINE:")
        .setItems(Constants.refineArrReservation, (dialog, position) -> {
          refineSelectedPos =  position;
          tvCategoryFilter.setText("REFINE: "+Constants.refineArrReservation[refineSelectedPos]);
          loadHistoryList();
        })
        .setNegativeButton("Cancel", null)
        .show();
  }
  @OnClick(R.id.tvSort_filter)
  void onClickSort(){
    new AlertDialog.Builder(this).setTitle("SORT BY:")
        .setItems(Constants.sortArrReservation, (dialog, position) -> {
          sortSelectedPos =  position;
          tvSortFilter.setText("SORT BY: "+Constants.sortArrReservation[sortSelectedPos]);
          loadHistoryList();
        })
        .setNegativeButton("Cancel", null)
        .show();
  }


}
