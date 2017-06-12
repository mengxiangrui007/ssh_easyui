package com.data.service.professional.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.professional.impl.ProfessionalDaoImpl;
import com.data.model.Professional;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.professional.IProfessionalService;
import com.data.share.initdata.StaticDataUtil;

@Service("iProfessionalService")
public class ProfessionalServiceImpl extends
		BaseSericeImpl<Professional, Integer> implements IProfessionalService {
	/**
	 * @Fields iProfessionalService : TODO(注入)
	 */
	@Autowired(required = true)
	private ProfessionalDaoImpl professionalDaoImpl;
	/**
	 * @Fields professionalList : TODO(集合)
	 */
	private List<Professional> professionalList = null;

	@Resource(name = "professionalDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Professional, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public List<Professional> queryProfessional(List<Integer> departmentIdList)
			throws Exception {
		/*
		 * List<Department> departmentList = StaticDataUtil.getDepartmentList();
		 * Department departmentTmp = null; for (int i = 0, len =
		 * departmentList.size(); i < len; i++) { departmentTmp =
		 * departmentList.get(i); for (Integer departmentId : departmentIdList)
		 * { if (departmentTmp.getId() == departmentId) {
		 * professionalList.addAll(departmentTmp .getProfessionalDepartments());
		 * } } }
		 */
		return professionalDaoImpl.queryProfessional(departmentIdList);
	}

	@Override
	public List<Professional> queryAllProfessional() throws Exception {
		return iBaseDao.getAll();
	}

	@Override
	public List<Professional> getStaticProfessional() throws Exception {
		return StaticDataUtil.getProfessionalList();
	}

	@Override
	public List<Professional> queryChoiceProfessional(List<Integer> asList)
			throws Exception {
		return professionalDaoImpl.queryChoiceProfessional(asList);
	}
}
