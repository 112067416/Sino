package com.quanta.sino.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.coco.sys.orm.Directory;

/**
 * <p>
 * 资料室表
 * </p>
 * <p>
 * create: 2011-1-8 下午02:16:44
 * </p>
 * 
 * @author 张良[jimsonhappy@126.com]
 * @version 1.0
 */
@Entity
@Table(name = "SINO_INFORM")
public class InformTp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID_", length = 40)
	private String id;

	/**
	 * 目录
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "DIRECTORY_", referencedColumnName = "ID_", nullable = false)
	private Directory directory;

	/**
	 * 关联附件
	 */
	@Column(name = "ATTACHMENT_", length = 40)
	private String attachment;

	/**
	 * 扩展名
	 */
	@Column(name = "EXT_", length = 32)
	private String ext;

	/**
	 * 资料名称
	 */
	@Column(name = "NAME_", length = 64, nullable = false)
	private String name;

	/**
	 * 备注
	 */
	@Column(name = "BZ_", length = 256)
	private String bz;

	/**
	 * 创建人代码
	 */
	@Column(name = "DDNO_", length = 16)
	private String ddno;

	/**
	 * 创建人名称
	 */
	@Column(name = "DDNM_", length = 32)
	private String ddnm;

	/**
	 * 创建时间
	 */
	@Column(name = "CREAT_")
	private Date creat;

	/**
	 * 授权用户
	 */
	@Column(name = "USERS_", length = 512)
	private String users;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the directory
	 */
	public Directory getDirectory() {
		return directory;
	}

	/**
	 * @param directory
	 *            the directory to set
	 */
	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * @param ext
	 *            the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz
	 *            the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the ddno
	 */
	public String getDdno() {
		return ddno;
	}

	/**
	 * @param ddno
	 *            the ddno to set
	 */
	public void setDdno(String ddno) {
		this.ddno = ddno;
	}

	/**
	 * @return the ddnm
	 */
	public String getDdnm() {
		return ddnm;
	}

	/**
	 * @param ddnm
	 *            the ddnm to set
	 */
	public void setDdnm(String ddnm) {
		this.ddnm = ddnm;
	}

	/**
	 * @return the creat
	 */
	public Date getCreat() {
		return creat;
	}

	/**
	 * @param creat
	 *            the creat to set
	 */
	public void setCreat(Date creat) {
		this.creat = creat;
	}

	/**
	 * @return the users
	 */
	public String getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(String users) {
		this.users = users;
	}

}