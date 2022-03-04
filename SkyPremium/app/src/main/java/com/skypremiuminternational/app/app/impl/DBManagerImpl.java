package com.skypremiuminternational.app.app.impl;

import com.skypremiuminternational.app.data.DBManager;
import com.skypremiuminternational.app.data.DbHelper;
import com.skypremiuminternational.app.data.database.FaqItemDao;
import com.skypremiuminternational.app.domain.models.faq.FaqItem;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DBManagerImpl implements DBManager {

  private final FaqItemDao faqItemDao;
  private final DbHelper dbHelper;

  @Inject
  public DBManagerImpl(DbHelper dbHelper) {
    this.dbHelper = dbHelper;
    faqItemDao = dbHelper.getFaqItemDao();
  }

  public void close() {
    dbHelper.close();
  }

  @Override
  public void saveFaqItem(List<FaqItem> value) {
    for (FaqItem faqItem : value) {
      try {
        faqItemDao.createOrUpdate(faqItem);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public List<FaqItem> getAllFaqItem() {
    try {
      return faqItemDao.getAll();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<FaqItem> getFaqItemByKeyword(String keyword) {
    try {
      return faqItemDao.getAllByKeyword(keyword);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
