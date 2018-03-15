/**
 * 
 */
package com.vatit.phonebook.services.ejb.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vatit.phonebook.exception.NoSuchUserException;
import com.vatit.phonebook.repositories.entity.ContactNumber;
import com.vatit.phonebook.repositories.entity.EmailAddress;
import com.vatit.phonebook.repositories.entity.User;
import com.vatit.phonebook.services.dto.ContactNumberDTO;
import com.vatit.phonebook.services.dto.PhoneBookDTO;
import com.vatit.phonebook.services.dto.UserDTO;
import com.vatit.phonebook.services.ejb.PhoneBookService;

/**
 * @author buhake.sindi
 *
 */
@Stateless
public class PhoneBookServiceImpl implements PhoneBookService {
	
	@PersistenceContext(name="phonebookPU")
	private EntityManager entityManager;
	
	/**
	 * 
	 */
	public PhoneBookServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Function<User, PhoneBookDTO> map() {
		
		return new Function<User, PhoneBookDTO>() {

			/* (non-Javadoc)
			 * @see java.util.function.Function#apply(java.lang.Object)
			 */
			@Override
			public PhoneBookDTO apply(User user) {
				// TODO Auto-generated method stub
				PhoneBookDTO phoneBook = new PhoneBookDTO();
				phoneBook.setContactNumbers(new ArrayList<>());
				if (user.getContactNumbers() != null) {
					for (ContactNumber contact : user.getContactNumbers()) {
						ContactNumberDTO contactDTO = new ContactNumberDTO();
						contactDTO.setContactNumber(contact.getContactNumber());
						contactDTO.setCountryCode(contact.getCountryCode());
						
						phoneBook.getContactNumbers().add(contactDTO);
					}
				}
				
				phoneBook.setEmailAddresses(new ArrayList<>());
				if (user.getEmailAddresses() != null) {
					for (EmailAddress email : user.getEmailAddresses()) {
						phoneBook.getEmailAddresses().add(email.getId());
					}
				}
				
				phoneBook.setUser(new UserDTO());
				phoneBook.getUser().setFamilyName(user.getFamilyName());
				phoneBook.getUser().setGivenName(user.getGivenName());
				phoneBook.getUser().setMiddleName(user.getMiddleName());
				return phoneBook;
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see com.vatit.phonebook.services.ejb.PhoneBookService#createNew(com.vatit.phonebook.services.dto.PhoneBookDTO)
	 */
	@Override
	public void createNew(PhoneBookDTO phoneBookDTO) {
		// TODO Auto-generated method stub
		if (phoneBookDTO == null) {
			throw new NullPointerException();
		}

		User user = new User();
		user.setFamilyName(phoneBookDTO.getUser().getFamilyName());
		user.setGivenName(phoneBookDTO.getUser().getGivenName());
		user.setMiddleName(phoneBookDTO.getUser().getMiddleName());
		
		if (phoneBookDTO.getContactNumbers() != null) {
			for (ContactNumberDTO contactNumberDTO : phoneBookDTO.getContactNumbers()) {
				ContactNumber contactNumber = new ContactNumber();
				contactNumber.setContactNumber(contactNumberDTO.getContactNumber());
				contactNumber.setCountryCode(contactNumberDTO.getCountryCode());
				user.addContactNumber(contactNumber);
			}
		}
		
		if (phoneBookDTO.getEmailAddresses() != null) {
			for (String emailAddress : phoneBookDTO.getEmailAddresses()) {
				EmailAddress email = new EmailAddress();
				email.setId(emailAddress);
				user.addEmailAddress(email);
			}
		}
		
		entityManager.persist(user);
	}

	/* (non-Javadoc)
	 * @see com.vatit.phonebook.services.ejb.PhoneBookService#getAllUsers()
	 */
	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = entityManager.createQuery("SELECT u FROm User u", User.class).getResultList();
		if (users == null) {
			return null;
		}
		
		final Function<User, UserDTO> mapper = new Function<User, UserDTO>() {

			/* (non-Javadoc)
			 * @see java.util.function.Function#apply(java.lang.Object)
			 */
			@Override
			public UserDTO apply(User user) {
				// TODO Auto-generated method stub
				UserDTO userDTO = new UserDTO();
				userDTO.setFamilyName(user.getFamilyName());
				userDTO.setGivenName(user.getGivenName());
				userDTO.setId(user.getId());
				userDTO.setMiddleName(user.getMiddleName());
				
				return userDTO;
			}
		};
		
		return users.stream().map(mapper).collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see com.vatit.phonebook.services.ejb.PhoneBookService#findByUserId(long)
	 */
	@Override
	public PhoneBookDTO findByUserId(long userId) {
		// TODO Auto-generated method stub
		User user = entityManager.find(User.class, userId);
		if (user == null) {
			throw new NoSuchUserException();
		}
		
		return map().apply(user);
	}

	/* (non-Javadoc)
	 * @see com.vatit.phonebook.services.ejb.PhoneBookService#findByEmailAddress(java.lang.String)
	 */
	@Override
	public PhoneBookDTO findByEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		User user = entityManager.createQuery("SELECT u FROM User u JOIN u.emailAddresses e WHERE e.id = ?1", User.class).setParameter(1, emailAddress).getSingleResult();
		if (user == null) {
			throw new NoSuchUserException();
		}
		
		return map().apply(user);
	}
}
