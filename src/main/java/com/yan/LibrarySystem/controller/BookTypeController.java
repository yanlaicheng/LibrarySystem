package com.yan.LibrarySystem.controller;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yan.LibrarySystem.po.BookType;
import com.yan.LibrarySystem.service.BookTypeService;

//图书类型管理控制层
@Controller
@RequestMapping("/BookType")
public class BookTypeController {

	private static Logger logger = Logger.getLogger(BookTypeController.class);
	
	
	@Resource
	BookTypeService bookTypeService;

	@InitBinder
	// 必须有一个参数WebDataBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
		binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() {
				return getValue() == null ? "" : getValue().toString();
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				Integer value = 0;
				if (null != text && !text.equals("")) {
					value = Integer.valueOf(text);
				}
				setValue(value);
			}
		});

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new BookType());
		return "BookType_add";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated BookType bookType, BindingResult br, Model model, HttpServletRequest request) {
		if (br.hasErrors()) {
			model.addAttribute(bookType);
			return "BookType_add";
		}
		try {
			bookTypeService.AddBookType(bookType);
			request.setAttribute("message", URLEncoder.encode("图书类型添加成功!", "utf-8"));
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
			try {
				request.setAttribute("error", URLEncoder.encode("图书类型添加失败!", "utf-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			return "error";
		}

	}

	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Integer currentPage, Model model, HttpServletRequest request) {
		try {
			if (currentPage == null || currentPage == 0)
				currentPage = 1;
			List<BookType> bookTypeList = bookTypeService.QueryBookTypeInfo(currentPage);
			/* 计算总的页数和总的记录数 */
			bookTypeService.CalculateTotalPageAndRecordNumber();
			/* 获取到总的页码数目 */
			int totalPage = bookTypeService.getTotalPage();
			/* 当前查询条件下总记录数 */
			int recordNumber = bookTypeService.getRecordNumber();
			request.setAttribute("bookTypeList", bookTypeList);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("recordNumber", recordNumber);
			request.setAttribute("currentPage", currentPage);
			return "BookType_query_result";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
			request.setAttribute("error", e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value = { "/frontlist" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String frontlist(Integer currentPage, Model model, HttpServletRequest request) {
		try {
			if (currentPage == null || currentPage == 0)
				currentPage = 1;
			List<BookType> bookTypeList = bookTypeService.QueryBookTypeInfo(currentPage);
			/* 计算总的页数和总的记录数 */
			bookTypeService.CalculateTotalPageAndRecordNumber();
			/* 获取到总的页码数目 */
			int totalPage = bookTypeService.getTotalPage();
			/* 当前查询条件下总记录数 */
			int recordNumber = bookTypeService.getRecordNumber();
			request.setAttribute("bookTypeList", bookTypeList);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("recordNumber", recordNumber);
			request.setAttribute("currentPage", currentPage);
			return "BookType_frontquery_result";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value = "/{bookTypeId}/update", method = RequestMethod.GET)
	public String update(@PathVariable int bookTypeId, Model model) {
		try {
			/* 根据主键bookTypeId获取BookType对象 */
			BookType bookType = bookTypeService.GetBookTypeByBookTypeId(bookTypeId);
			model.addAttribute(bookType);
			return "BookType_modify";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(value = "/{bookTypeId}/frontshow", method = RequestMethod.GET)
	public String frontshow(@PathVariable int bookTypeId, Model model) {
		try {
			/* 根据主键bookTypeId获取BookType对象 */
			BookType bookType = bookTypeService.GetBookTypeByBookTypeId(bookTypeId);
			model.addAttribute(bookType);
			return "BookType_frontshow";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(value = "/{bookTypeId}/update", method = RequestMethod.POST)
	public String update(@Validated BookType bookType, BindingResult br, Model model, HttpServletRequest request) {

		if (br.hasErrors()) {
			model.addAttribute(bookType);
			return "BookType_modify";
		}
		try {
			bookTypeService.UpdateBookType(bookType);
			request.setAttribute("message", "图书类型添加成功!");
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "图书类型更新失败!");
			return "error";
		}
	}

	@RequestMapping(value = "/{bookTypeId}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable int bookTypeId, HttpServletRequest request) {
		try {
			bookTypeService.DeleteBookType(bookTypeId);
			request.setAttribute("message", "图书类型删除成功!");
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "图书类型删除失败!");
			return "error";
		}
	}

}
