package com.paintme.services;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;
import com.paintme.domain.models.statuses.UserStatuses;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

	private String userProperties = Properties.Files.SESSION.toString();

	private String loginPropertyName = Properties.UserProperties.LOGIN.toString();
	private String passwordHashPropertyName = Properties.UserProperties.PASSWORD.toString();

	private UserRepository userRepository;

	private PropertiesService propertiesService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PropertiesService propertiesService) {
		this.userRepository = userRepository;

        this.propertiesService = propertiesService;
        this.propertiesService.setPropertiesFileName(this.userProperties);
    }

	@Override
	public boolean changePassword(User user, String oldPassword, String newPassword)
			throws PaintMEException {
		try {
			if (!(Objects.equals(Hashing.getSecurePassword(
					oldPassword, user.getPasswordSalt(), "SHA-256"),
					user.getPasswordHash()))) {
				return false;
			}
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for password encoding chosen. " +
							exception.getMessage(), exception);
		}

		byte[] newSalt;
		try {
			newSalt = Hashing.getSalt("SHA1PRNG");
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for salt generation chosen. " +
							exception.getMessage(), exception);
		}

		String newPasswordHash;
		try {
			newPasswordHash = Hashing.getSecurePassword(
					newPassword, newSalt, "SHA-256");
		} catch (NoSuchAlgorithmException exception) {
			throw new PaintMEException(
					"Unknown algorithm for password encoding chosen. " +
							exception.getMessage(), exception);
		}

		user.setPasswordSalt(newSalt);
		user.setPasswordHash(newPasswordHash);

		this.userRepository.save(user);

		return true;
	}

	@Override
	public User getSessionUser() throws PaintMEException {
		String login = this.propertiesService.getProperty(this.loginPropertyName);
		String passwordHash = this.propertiesService.getProperty(this.passwordHashPropertyName);

		User user = null;
		if (login != null && passwordHash != null) {
			user = this.userRepository
					.findByLoginAndPasswordHash(login, passwordHash);
			if (user == null) {
				throw new PaintMEException(
						"User with login " + login +
								" and password hash " + passwordHash +
								" doesn't exist.");
			}
		}

		return user;
	}

	@Override
	public void setSessionUser(String login, String password) throws PaintMEException {
        User user = this.userRepository.findByLogin(login);

        if (user != null) {
            if (!this.isPasswordCorrect(user, password)) {
                throw new PaintMEException(
                        "User with login " + login +
                                " has different password (not " + password + ")");
            } else {
                if (user.getStatus() != UserStatuses.OFFLINE) {
                    throw new PaintMEException(
                            "User with login '" + login +
                                    "' and password '" + password +
                                    "' is already signed in.");
                } else {
                    user.setStatus(UserStatuses.ONLINE);
                    this.userRepository.save(user);

                    this.propertiesService.addProperty(this.loginPropertyName, user.getLogin());
                    this.propertiesService.addProperty(this.passwordHashPropertyName, user.getPasswordHash());
                }
            }
        }
        else {
            throw new PaintMEException(
				"User with login " + login +
						" doesn't exist.");
		}
	}

	@Override
	public void removeSessionUser() throws PaintMEException {
	    User sessionUser = this.getSessionUser();

	    if (sessionUser != null)
        {
            if (sessionUser.getStatus() == UserStatuses.OFFLINE){
                throw new PaintMEException(
                        "User with login " + sessionUser.getLogin() +
                                " is already signed out.");
            }
            else
            {
                sessionUser.setStatus(UserStatuses.OFFLINE);
                this.userRepository.save(sessionUser);

                this.propertiesService.deleteProperty(this.loginPropertyName);
                this.propertiesService.deleteProperty(this.passwordHashPropertyName);
            }
        }
        else {
	        throw new PaintMEException(
                "No session user found.");
        }
	}

	private boolean isPasswordCorrect(User user, String password) throws PaintMEException {
        String passwordHash;
        try {
            passwordHash = Hashing.getSecurePassword(password, user.getPasswordSalt(), "SHA-256");
        }
        catch (NoSuchAlgorithmException exception){
            throw new PaintMEException("Unknown algorithm was used for getting user's password hash.");
        }

        return  Objects.equals(user.getPasswordHash(), passwordHash);
    }
}
