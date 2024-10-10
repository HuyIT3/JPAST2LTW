package vn.iostar.services;

import java.util.List;

import vn.iostar.entity.Video;

public interface IVideoService {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByTitlename(String vdtitle);

	List<Video> findAll();

	Video findById(String vidid);

	void delete(String vidid) throws Exception;

	void update(Video video);

	void insert(Video video);
}
