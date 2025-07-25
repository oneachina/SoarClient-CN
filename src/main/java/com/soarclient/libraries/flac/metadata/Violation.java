package com.soarclient.libraries.flac.metadata;

/**
 * libFLAC - Free Lossless Audio Codec library
 * Copyright (C) 2001,2002,2003  Josh Coalson
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 */

/**
 * Meta data format violation exception.
 * 
 * @author kc7bfi
 */
public class Violation extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public Violation() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param msg The error message
	 */
	public Violation(String msg) {
		super(msg);
	}

	/**
	 * Constructor.
	 * 
	 * @param err The causing exception
	 */
	public Violation(Throwable err) {
		super(err);
	}

	/**
	 * Constructor.
	 * 
	 * @param msg The error message
	 * @param err The causing exception
	 */
	public Violation(String msg, Throwable err) {
		super(msg, err);
	}

}
