package com.waci.erp.shared.api;

import java.util.List;


public class ResponseList<T> extends BaseResponse {

	private int totalItems;
	private int currentPage;
	private int totalPages;
	private List<T> records;
	private List<T> data;

	public ResponseList(List<T> records, int totalItems, int currentPage, int totalPages) {
		this.totalItems = totalItems;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.records = records;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
