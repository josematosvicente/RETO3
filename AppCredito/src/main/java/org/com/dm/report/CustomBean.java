package org.com.dm.report;

/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class CustomBean
{


	/**
	 *
	 */
	private String city;
	private Integer id;
	private String name;
	private String street;
	private String abc;


	/**
	 *
	 */


	/**
	 *
	 */
	public CustomBean getMe()
	{
		return this;
	}


	public CustomBean(String city, Integer id, String name, String street) {
		super();
		this.city = city;
		this.id = id;
		this.name = name;
		this.street = street;
	}


	/**
	 *
	 */
	public String getCity()
	{
		return city;
	}


	/**
	 *
	 */
	public Integer getId()
	{
		return id;
	}


	/**
	 *
	 */
	public String getName()
	{
		return name;
	}


	/**
	 *
	 */
	public String getStreet()
	{
		return street;
	}


	public String getAbc() {
		return abc;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public void setAbc(String abc) {
		this.abc = abc;
	}



}
