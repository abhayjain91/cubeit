package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import daos.AllDao;
import models.ContentModel;
import models.CubeModel;
import models.UserModel;

@RestController
public class AllController {

	@Autowired
	private AllDao allDao;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody UserModel userModel, BindingResult result, SessionStatus status) {
		System.out.println("inside add user controller");
		allDao.addUser(userModel);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{userId}/cube", method = RequestMethod.POST)
	public ResponseEntity<Void> addCube(@PathVariable Integer userId, @RequestBody String cubeName,
			BindingResult result, SessionStatus status) {
		CubeModel cubeModel = new CubeModel();
		cubeModel.setName(cubeName);
		cubeModel.setUserId(userId);
		allDao.addCube(cubeModel);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{userId}/content", method = RequestMethod.POST)
	public ResponseEntity<Void> addContent(@PathVariable Integer userId, @RequestBody String link, BindingResult result,
			SessionStatus status) {
		ContentModel contentModel = new ContentModel();
		contentModel.setLink(link);
		contentModel.setUserId(userId);
		allDao.addContent(contentModel);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{userId}/cube/{cubeId}/content", method = RequestMethod.POST)
	public ResponseEntity<Void> addContentToCube(@PathVariable Integer userId, @PathVariable Integer cubeId,
			@RequestBody Integer contentId, BindingResult result, SessionStatus status) {
		ContentModel contentModel = new ContentModel();
		contentModel.setId(contentId);
		contentModel.setUserId(userId);
		contentModel.setCubeId(cubeId);
		allDao.updateContent(contentModel);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{userId}/cube/{cubeId}/content/{contentId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContentFromCube(@PathVariable Integer userId, @PathVariable Integer cubeId,
			@PathVariable Integer contentId, BindingResult result, SessionStatus status) {
		ContentModel contentModel = new ContentModel();
		contentModel.setUserId(userId);
		contentModel.setCubeId(null); // removing cubeid from content.
		contentModel.setId(contentId);
		allDao.updateContent(contentModel);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
	}
	
}
