package com.skypremiuminternational.app.app.utils.listener;


import com.skypremiuminternational.app.domain.models.PaymentDetail;

public interface PaymentDetailCollector {
  PaymentDetail collect();

  void scrollToTop();
}
