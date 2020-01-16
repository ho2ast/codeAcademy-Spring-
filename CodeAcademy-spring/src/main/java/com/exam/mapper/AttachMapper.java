package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.domain.AttachVO;

public interface AttachMapper {
	
	@Insert("INSERT INTO attaches (uuid, uploadpath, filename, filetype, bno) VALUES (#{uuid}, #{uploadpath}, #{filename}, #{filetype}, #{bno})")
	public void insertAttach(AttachVO attachVO);
	
	public List<AttachVO> getAttach(int bno);
	
	@Delete("DELETE FROM attaches WHERE bno = #{bno}")
	public void deleteAttachByBno(int bno);
	
	@Delete("DELETE FROM attaches WHERE uuid = #{uuid}")
	public void deleteAttachByUuid(String uuid);
	
	@Select("SELECT CONCAT(uuid,'_',filename) AS str FROM attaches WHERE bno = #{bno}")
	public List<String> getUuidAndFilenameByBno(int bno);
	
	@Select("SELECT DISTINCT(uploadpath) FROM attaches WHERE bno = #{bno}")
	public List<String> getUploadpathByBno(int bno);
}
