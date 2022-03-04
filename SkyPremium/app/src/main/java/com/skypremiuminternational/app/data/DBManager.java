package com.skypremiuminternational.app.data;


import com.skypremiuminternational.app.domain.models.faq.FaqItem;

import java.util.List;

public interface DBManager {

    void saveFaqItem(List<FaqItem> value);

    List<FaqItem> getAllFaqItem();

    List<FaqItem> getFaqItemByKeyword(String keyword);

}
