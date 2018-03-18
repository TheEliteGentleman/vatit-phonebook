/**
 * 
 */
package com.vatit.phonebook.services.ejb;

import java.util.List;

import com.vatit.phonebook.services.dto.PhoneBookDTO;
import com.vatit.phonebook.services.dto.UserDTO;

/**
 * @author buhake.sindi
 *
 */
public interface PhoneBookService {

	public void createNew(final PhoneBookDTO phoneBookDTO);
	
	public void deletePhoneBook(final long userId);
	
	public List<UserDTO> getAllUsers();
	
	public PhoneBookDTO findByUserId(final long userId);
	
	public PhoneBookDTO findByEmailAddress(final String emailAddress);
}
