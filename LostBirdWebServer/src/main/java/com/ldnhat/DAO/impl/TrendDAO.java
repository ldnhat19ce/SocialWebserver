package com.ldnhat.DAO.impl;

import com.ldnhat.DAO.ITrendDAO;
import com.ldnhat.mapper.impl.TrendMapper;
import com.ldnhat.model.TrendModel;

import java.util.List;

public class TrendDAO extends AbstractDAO<TrendModel> implements ITrendDAO {

    @Override
    public List<TrendModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM trends t ");
        sql.append("ORDER BY t.amount DESC limit 7");
        return query(sql.toString(), new TrendMapper(), "");
    }

    @Override
    public Boolean checkHashtag(String hashtag) {
        StringBuilder sql = new StringBuilder("SELECT * FROM trends t ");
        sql.append("WHERE t.hashtag = ?");

        Object[] params = {hashtag};

        List<TrendModel> trendModels = query(sql.toString(), new TrendMapper(), "", params);

        if (!trendModels.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void update(TrendModel trendModel) {
        StringBuilder sql = new StringBuilder("UPDATE trends t ");
        sql.append("SET t.hashtag = ?, t.amount = ? ");
        sql.append("WHERE t.trend_id = ?");

        Object[] params = {trendModel.getHashtag(), trendModel.getAmount() + 1,
                        trendModel.getId()};

        update(sql.toString(), params);
    }

    @Override
    public TrendModel findByHashtag(String hashtag) {
        StringBuilder sql = new StringBuilder("SELECT * FROM trends t ");
        sql.append("WHERE t.hashtag = ?");

        Object[] params = {hashtag};

        List<TrendModel> trendModels = query(sql.toString(), new TrendMapper(), "", params);
        return trendModels.isEmpty() ? null : trendModels.get(0);
    }

    @Override
    public Long save(TrendModel trendModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO trends");
        sql.append("(hashtag, amount, create_date) ");
        sql.append("VALUES(?,?,?)");

        Object[] params = {trendModel.getHashtag(), trendModel.getAmount(), trendModel.getCreateDate()};
        return insert(sql.toString(), params);
    }

    @Override
    public TrendModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM trends t ");
        sql.append("WHERE t.trend_id = ?");

        Object[] params = {id};

        List<TrendModel> trendModels = query(sql.toString(), new TrendMapper(), "", params);
        return trendModels.isEmpty() ? null : trendModels.get(0);
    }
}
