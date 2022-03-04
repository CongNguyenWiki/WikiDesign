package com.skypremiuminternational.app.app.features.membership;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skypremiuminternational.app.R;
import com.skypremiuminternational.app.domain.models.mymembership_statement.MyMembershipStatementItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMembershipViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_membership_tier)
    TextView tv_membership_tier;
    @BindView(R.id.tv_fee)
    TextView tv_fee;
    @BindView(R.id.tv_start_date)
    TextView tv_start_date;
    @BindView(R.id.tv_end_date)
    TextView tv_end_date;
    @BindView(R.id.tv_payment_date)
    TextView tv_payment_date;
    @BindView(R.id.tv_next_membership_fee_payment_date)
    TextView tv_next_membership_fee_payment_date;
    @BindView(R.id.tv_payment_plan)
    TextView tv_payment_plan;
    @BindView(R.id.tv_payment_status)
    TextView tv_payment_status;
    @BindView(R.id.tv_associated_code)
    TextView tv_associated_code;
    @BindView(R.id.tv_view_invoice)
    TextView tv_view_invoice;
    @BindView(R.id.tv_type)
    TextView tv_type;



    private MyMembershipStatementItem myMembershipStatementItem;

    public MyMembershipViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(final MyMembershipStatementItem item,String showUrl,boolean isCancel,boolean isUpGrade,boolean isDownGrade, boolean isRenew){
        this.myMembershipStatementItem = item;


        tv_membership_tier.setText(item.getMembershipTier());

        tv_type.setText(item.getType());
//        tv_fee.setText(new StringBuilder("SGD $").append(item.getMembershipFee()));
        tv_fee.setText(item.getMembershipFee());
        if (item.getMembershipStartDate() != null && !item.getMembershipStartDate().equalsIgnoreCase(""))
        {
            tv_start_date.setText(parseDateFormat(item.getMembershipStartDate()));

        }
        else
        {
            tv_start_date.setText("-");
        }

        if (item.getMembershipEndDate() != null && !item.getMembershipEndDate().equalsIgnoreCase(""))
        {
            tv_end_date.setText(parseDateFormat(item.getMembershipEndDate()));

        }
        else
        {
            tv_end_date.setText("-");
        }

        if (item.getMembershipFeePaymentDate() != null && !item.getMembershipFeePaymentDate().equalsIgnoreCase(""))
        {
            tv_payment_date.setText(parseDateFormat(item.getMembershipFeePaymentDate()));

        }
        else
        {
            tv_payment_date.setText("-");
        }

        if (item.getNextMembershipFeePaymentDate() != null && !item.getNextMembershipFeePaymentDate().equalsIgnoreCase(""))
        {
            String title = getColoredSpanned(parseDateFormat(item.getNextMembershipFeePaymentDate()),"#5A5A5A");
            String cancel = getColoredSpanned("(Cancel my membership)","#BCBCBC");
            if (showUrl.equals("1") && isCancel == true){
                tv_next_membership_fee_payment_date.setText(Html.fromHtml(title + "<br>" + cancel));
                tv_next_membership_fee_payment_date.setClickable(true);
            }
            else if (showUrl.equals("2") && isCancel == true){
                tv_next_membership_fee_payment_date.setText(Html.fromHtml(title + "<br>" + cancel));
                tv_next_membership_fee_payment_date.setClickable(true);
            }
            else {
                tv_next_membership_fee_payment_date.setText(Html.fromHtml(title));
            }

        }

        else
        {
            String cancel = getColoredSpanned("(Cancel my membership)","#BCBCBC");
            if (showUrl.equals("1") && isCancel == true){
                tv_next_membership_fee_payment_date.setText(Html.fromHtml("-" + "<br>" + cancel));
                tv_next_membership_fee_payment_date.setClickable(true);
            }
            else if (showUrl.equals("2") && isCancel == true){
                tv_next_membership_fee_payment_date.setText(Html.fromHtml("-" + "<br>" + cancel));
                tv_next_membership_fee_payment_date.setClickable(true);
            }
            else {
                tv_next_membership_fee_payment_date.setText("-");
            }

        }

        if (item.getPaymentPlan() != null && !TextUtils.isEmpty(item.getPaymentPlan())){
            String title = getColoredSpanned(item.getPaymentPlan(),"#5A5A5A");
            String renew = getColoredSpanned("(Change subscription plan)","#CBA84A");

            if (showUrl.equals("1") && isRenew == true){
                tv_payment_plan.setText(Html.fromHtml(title + "<br>" + renew));
                tv_payment_plan.setClickable(true);
            }

             else if (showUrl.equals("2") && isRenew == true){
                tv_payment_plan.setText(Html.fromHtml(title + "<br>" + renew));
                tv_payment_plan.setClickable(true);
            }

            else {
                tv_payment_plan.setText(Html.fromHtml(title));
            }


        }
        else {

            String renew = getColoredSpanned("(Change subscription plan)","#CBA84A");

            if (showUrl.equals("1") && isRenew == true){
                tv_payment_plan.setText(Html.fromHtml("-" + "<br>" + renew));
                tv_payment_plan.setClickable(true);
            }

            else if (showUrl.equals("2") && isRenew == true){
                tv_payment_plan.setText(Html.fromHtml("-" + "<br>" + renew));
                tv_payment_plan.setClickable(true);
            }
            else {
                tv_payment_plan.setText("-");
            }

        }




        if (item.getPaymentStatus()  != null && item.getPaymentStatus().equalsIgnoreCase("PD")){
            tv_payment_status.setText("Paid");


        }
        if (item.getPaymentStatus()  != null && item.getPaymentStatus().equalsIgnoreCase("UP")){
            String title = getColoredSpanned("Unpaid","#5A5A5A");
            String updowngrade = getColoredSpanned("(Click here to make payment)","#CBA84A");

            if (showUrl.equals("1") && isUpGrade == true){
                tv_payment_status.setText(Html.fromHtml(title + "<br>" + updowngrade));
                tv_payment_status.setClickable(true);
            }
            else if (showUrl.equals("2")  && isUpGrade == true){
                tv_payment_status.setText(Html.fromHtml(title + "<br>" + updowngrade));
                tv_payment_status.setClickable(true);
            }
            else if (showUrl.equals("1") && isDownGrade == true){
                tv_payment_status.setText(Html.fromHtml(title + "<br>" + updowngrade));
                tv_payment_status.setClickable(true);
            }
            else if (showUrl.equals("2")  && isDownGrade == true){
                tv_payment_status.setText(Html.fromHtml(title + "<br>" + updowngrade));
                tv_payment_status.setClickable(true);
            }
            else {
                tv_payment_status.setText(Html.fromHtml(title));
            }





        }
        if (item.getPaymentStatus()  != null && item.getPaymentStatus().equalsIgnoreCase("PE")){
            tv_payment_status.setText("Pending");



        }

        if (item.getMembershipFeeInvoice() != null && !TextUtils.isEmpty(item.getMembershipFeeInvoice())){
            tv_view_invoice.setVisibility(View.VISIBLE);

        }
        else {
            tv_view_invoice.setVisibility(View.GONE);
        }


        if (item.getAssociatedCode() != null && !item.getAssociatedCode().equalsIgnoreCase(""))
        {
            tv_associated_code.setText(item.getAssociatedCode());

        }
        else
        {
            tv_associated_code.setText("-");
        }




    }

    private String parseDateFormat(String time){
        String inputPattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat("d");
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if(str.endsWith("1") && !str.endsWith("11"))
            format = new SimpleDateFormat("d'st' MMM yyyy", Locale.US);
        else if(str.endsWith("2") && !str.endsWith("12"))
            format = new SimpleDateFormat("d'nd' MMM yyyy",Locale.US);
        else if(str.endsWith("3") && !str.endsWith("13"))
            format = new SimpleDateFormat("d'rd' MMM yyyy",Locale.US);
        else
            format = new SimpleDateFormat("d'th' MMM yyyy",Locale.US);

        String yourDate = format.format(date);

        return yourDate;
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }



}
