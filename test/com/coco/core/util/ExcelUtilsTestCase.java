package com.coco.core.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.junit.Test;

import com.coco.core.excel.api.ExcelDataExecuter;

/**
 * <p>
 * Excel工具测试
 * </p>
 * <p>
 * create: 2011-1-28 上午11:22:10
 * </p>
 * 
 * @author 许德建[xudejian@126.com]
 * @version 1.0
 */
public class ExcelUtilsTestCase {

	@Test
	public void fillData() {

		ExcelVO data = new ExcelVO();
		ExcelPerson person;
		List<ExcelPerson> persons = new ArrayList<ExcelPerson>();
		data.setPersons(persons);

		ExcelPersonExperience experience;
		List<ExcelPersonExperience> experiences;

		person = new ExcelPerson();
		persons.add(person);
		person.setName("许德建");
		person.setAge(30);
		person.setSpecialty("IT");
		experiences = new ArrayList<ExcelPersonExperience>();
		person.setExperiences(experiences);
		experience = new ExcelPersonExperience();
		experiences.add(experience);
		experience.setTime("2003-2004");
		experience.setComp("联想");
		experience.setPost("程序员");
		experience = new ExcelPersonExperience();
		experiences.add(experience);
		experience.setTime("2004-2006");
		experience.setComp("持名");
		experience.setPost("项目经理");
		experience = new ExcelPersonExperience();
		experiences.add(experience);
		experience.setTime("2006-2007");
		experience.setComp("方欣");
		experience.setPost("架构师");

		person = new ExcelPerson();
		persons.add(person);
		person.setName("罗海伶");
		person.setAge(30);
		person.setSpecialty("医学");
		experiences = new ArrayList<ExcelPersonExperience>();
		person.setExperiences(experiences);
		experience = new ExcelPersonExperience();
		experiences.add(experience);
		experience.setTime("2007-2011");
		experience.setComp("省医");
		experience.setPost("住院医师");

		String path = "classpath:com/coco/core/util/ExcelTemplate.xls";
		String outPath = "E:\\out.xls";
		OutputStream os = null;
		try {
			os = new FileOutputStream(outPath);
			ExcelUtils.fillData(path, data, new PersonExcelDataExecuter(), os);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (os != null) {
				try {
					os.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	public static class PersonExcelDataExecuter implements
			ExcelDataExecuter<ExcelVO> {

		@Override
		public void execute(HSSFSheet sheet, ExcelVO data) {
			HSSFRow row, childRow;
			int rowIndex = 2;
			for (ExcelPerson p : data.getPersons()) {
				row = sheet.createRow(rowIndex++);
				ExcelUtils.setCellValue(row.createCell(0), p.getName());
				ExcelUtils.setCellValue(row.createCell(1), p.getAge());
				childRow = null;
				for (ExcelPersonExperience exp : p.getExperiences()) {
					if (childRow == null) {
						childRow = row;
					}
					else {
						childRow = sheet.createRow(rowIndex++);
					}
					ExcelUtils.setCellValue(childRow.createCell(2), exp.getTime());
					ExcelUtils.setCellValue(childRow.createCell(3), exp.getComp());
					ExcelUtils.setCellValue(childRow.createCell(4), exp.getPost());
				}
				ExcelUtils.setCellValue(row.createCell(5), p.getSpecialty());
			}
		}

	}

	public static class ExcelVO {

		private List<ExcelPerson> persons;

		public List<ExcelPerson> getPersons() {
			return persons;
		}

		public void setPersons(List<ExcelPerson> persons) {
			this.persons = persons;
		}

	}

	public static class ExcelPerson {
		private String name;

		private int age;

		private String specialty;

		private List<ExcelPersonExperience> experiences;

		public List<ExcelPersonExperience> getExperiences() {
			return experiences;
		}

		public void setExperiences(List<ExcelPersonExperience> experiences) {
			this.experiences = experiences;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getSpecialty() {
			return specialty;
		}

		public void setSpecialty(String specialty) {
			this.specialty = specialty;
		}

	}

	public static class ExcelPersonExperience {

		private String time;

		private String comp;

		private String post;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getComp() {
			return comp;
		}

		public void setComp(String comp) {
			this.comp = comp;
		}

		public String getPost() {
			return post;
		}

		public void setPost(String post) {
			this.post = post;
		}

	}
}
