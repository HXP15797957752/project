package cn.jxau.service;

import java.util.List;

import cn.jxau.entity.Record;

public interface RecordService {
	public void vote(List<Record> records) throws Exception;
}
