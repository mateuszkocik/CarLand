package com.carland.dao;

import java.util.List;

import com.carland.entity.Advert;

public interface AdvertDao {

	public void saveAdvert(Advert advert);

	public List<Advert> getActiveAdverts();

	public Advert getAdvertById(int id);

	public void deleteAdvert(Advert advert);

	public Advert getOnePendingAdvert();
}
