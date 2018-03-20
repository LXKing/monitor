package com.edata.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.baseinfo.DictionaryItemDto;
import com.edata.monitor.dao.baseinfo.IDictionaryDao;
import com.edata.monitor.domain.baseinfo.DictionaryItem;
import com.edata.monitor.domain.baseinfo.DictionaryItemInfo;

/**
 * 数据字典服务类
 * 
 * @author yangzs
 *
 */
@Service
public class DictionaryService {
	@Autowired
	private IDictionaryDao dictionaryDao;

	/**
	 * 读取指定类型字典
	 * 
	 * @param kind
	 *            字典类型
	 */
	public List<DictionaryItemInfo> list(int kind) {
		List<DictionaryItemDto> list = dictionaryDao.list(kind);
		List<DictionaryItemInfo> dictionarys = new ArrayList<DictionaryItemInfo>(list.size());
		for (DictionaryItemDto dto : list) {
			DictionaryItemInfo info = new DictionaryItemInfo();
			info.setCode(dto.code);
			info.setId(dto.id);
			info.setIndex(dto.index);
			info.setName(dto.name);
			info.setPid(dto.pid);
			dictionarys.add(info);
		}
		return dictionarys;
	}

	/**
	 * 创建新的字典项
	 */
	@ServiceMethod(id = "baseinfo.dictionary.create", pid = "baseinfo.dictionary", name = "创建新的字典项")
	@Transactional
	public void create(DictionaryItem item) {
		DictionaryItemDto dto = new DictionaryItemDto();
		dto.code = item.getCode();
		dto.index = item.getIndex();
		dto.kind = item.getKind();
		dto.name = item.getName();
		dto.pid = item.getPid();

		dictionaryDao.create(dto);
	}

	/**
	 * 读取字典项资料
	 * 
	 * @param id
	 *            字典项唯一编号
	 */
	public DictionaryItem fetch(long id) {
		DictionaryItemDto dto = dictionaryDao.fetch(id);
		DictionaryItem item = new DictionaryItem();
		item.setId(dto.id);
		item.setCode(dto.code);
		item.setIndex(dto.index);
		item.setKind(dto.kind);
		item.setName(dto.name);
		item.setPid(dto.pid);
		item.setEditTime(dto.editTime);

		return item;
	}

	/**
	 * 更新字典项资料
	 */
	@ServiceMethod(id = "baseinfo.dictionary.update", pid = "baseinfo.dictionary", name = "修改字典项")
	@Transactional
	public void update(DictionaryItem item) {
		DictionaryItemDto dto = new DictionaryItemDto();
		dto.id = item.getId();
		dto.code = item.getCode();
		dto.index = item.getIndex();
		dto.kind = item.getKind();
		dto.name = item.getName();
		dto.pid = item.getPid();
		dto.editTime = item.getEditTime();

		int rows = dictionaryDao.update(dto);
		if (rows != 1)
			throw new RuntimeException(Errors.anotherEdited);
	}

	/**
	 * 删除字典项
	 * 
	 * @param id
	 *            字典项唯一编号
	 */
	@ServiceMethod(id = "baseinfo.dictionary.delete", pid = "baseinfo.dictionary", name = "删除字典项")
	@Transactional
	public void delete(long id) {
		dictionaryDao.delete(id);
	}

	/**
	 * 是否已存在字典项
	 * 
	 * @param name
	 *            字典项名称
	 * @param kind
	 *            字典类型
	 */
	public boolean exist(String name, int kind) {
		return dictionaryDao.exist(name, kind);
	}

	/**
	 * 是否已存在字典项
	 * 
	 * @param name
	 *            字典项名称
	 * @param kind
	 *            字典类型
	 * @param id
	 *            字典项唯一编号
	 */
	public boolean exist(String name, int kind, long id) {
		return dictionaryDao.existOutId(name, kind, id);
	}

	/**
	 * 移动字典项
	 * 
	 * @param id
	 *            字典项唯一编号
	 * @param pid
	 *            父字典项唯一编号
	 */
	@Transactional
	public void move(long id, Long pid) {
		dictionaryDao.move(id, pid);
	}
}
