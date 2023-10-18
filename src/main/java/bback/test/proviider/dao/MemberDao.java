package bback.test.proviider.dao;

import bback.module.ourbatis.persistance.OurbatisCrudHelper;
import bback.test.proviider.dao.scheme.MemberTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao extends OurbatisCrudHelper<MemberTable, String> {

}
