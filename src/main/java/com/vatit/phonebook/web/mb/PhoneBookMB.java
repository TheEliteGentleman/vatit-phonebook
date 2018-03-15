/**
 * 
 */
package com.vatit.phonebook.web.mb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vatit.phonebook.services.dto.ContactNumberDTO;
import com.vatit.phonebook.services.dto.PhoneBookDTO;
import com.vatit.phonebook.services.dto.UserDTO;
import com.vatit.phonebook.services.ejb.PhoneBookService;

/**
 * @author buhake.sindi
 *
 */
@RequestScoped
@Named("phoneBook")
public class PhoneBookMB {
	
	private static final Logger LOGGER = Logger.getLogger(PhoneBookMB.class.getName());

	@Inject
	private PhoneBookService phoneBookService;
	
	private UserDTO userForm;
	
	private ContactNumberDTO contactNumberForm;
	
	private String emailAddress;
	
	private List<UserDTO> users;
	
	/**
	 * 
	 */
	public PhoneBookMB() {
		super();
		// TODO Auto-generated constructor stub
		userForm = new UserDTO();
		contactNumberForm = new ContactNumberDTO();
	}

	@PostConstruct
	protected void init() {
		users = phoneBookService.getAllUsers();
	}
	
	public String createNew() {
		PhoneBookDTO phoneBook = new PhoneBookDTO();
		phoneBook.setContactNumbers(new ArrayList<>());
		phoneBook.getContactNumbers().add(contactNumberForm);
		phoneBook.setEmailAddresses(new ArrayList<>());
		phoneBook.getEmailAddresses().add(emailAddress);
		phoneBook.setUser(userForm);
		
		phoneBookService.createNew(phoneBook);
		init();
		
		return "index";
	}

	/**
	 * @return the userForm
	 */
	public UserDTO getUserForm() {
		return userForm;
	}

	/**
	 * @param userForm the userForm to set
	 */
	public void setUserForm(UserDTO userForm) {
		this.userForm = userForm;
	}

	/**
	 * @return the contactNumberForm
	 */
	public ContactNumberDTO getContactNumberForm() {
		return contactNumberForm;
	}

	/**
	 * @param contactNumberForm the contactNumberForm to set
	 */
	public void setContactNumberForm(ContactNumberDTO contactNumberForm) {
		this.contactNumberForm = contactNumberForm;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the users
	 */
	public List<UserDTO> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
}
