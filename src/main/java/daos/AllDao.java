package daos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.ContentModel;
import models.CubeModel;
import models.UserModel;

@Repository
public class AllDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(UserModel userModel) {
		System.out.println("inside add user user dao.");
		sessionFactory.getCurrentSession().save(userModel);
	}

	public void addCube(CubeModel cubeModel) {
		sessionFactory.getCurrentSession().save(cubeModel);
	}

	public void addContent(ContentModel contentModel) {
		sessionFactory.getCurrentSession().save(contentModel);
	}

	public void updateContent(ContentModel contentModel) {
		sessionFactory.getCurrentSession().update(contentModel);
	}
	
	

}
