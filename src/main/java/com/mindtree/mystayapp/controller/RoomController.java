package com.mindtree.mystayapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystayapp.exception.ApplicationException;
import com.mindtree.mystayapp.model.Response;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.service.RoomService;

/**
 * @author RShaw
 *
 *         Room Controller: To handle activities on Room Entity like Edit a room
 *         details, Add a new Room and Delete an existing room.
 */
@RestController
@RequestMapping(value = "/mystayapp")
public class RoomController {

	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	private RoomService roomService;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ApplicationException
	 *             Get a room detail
	 */

	@RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
	public ResponseEntity<Room> getRoomsById(@PathVariable("id") long id) throws ApplicationException {
		logger.info("Retrieving room details for room id:" + id);
		Room room = roomService.getRoomsById(id);
		if (room == null) {
			logger.debug("Room with id " + id + " doesn't exists");
			throw new ApplicationException("Room with id " + id + " doesn´t exist");
		}
		logger.info("Room found for room id:" + id);
		return new ResponseEntity<>(room, HttpStatus.OK);

	}

	/**
	 * 
	 * @param room
	 * @return
	 * @throws ApplicationException
	 *             Add a room into existing hotel
	 */
	@RequestMapping(value = "/room", method = RequestMethod.POST)
	public ResponseEntity<Room> addRoom(@Valid @RequestBody Room room) throws ApplicationException {
		logger.info("Adding room...");

		Room addedRoom = new Room();

		try {
			addedRoom = roomService.addRoom(room);
			logger.info("Room addedd succeccfully with id : " + addedRoom.getRoomId());
			return new ResponseEntity<>(addedRoom, HttpStatus.CREATED);
		} catch (ApplicationException e) {
			logger.debug("saving room failed...");
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
	}

	/**
	 * 
	 * @param room
	 * @return
	 * @throws ApplicationException
	 * 
	 *             Update the details for an existing room
	 */
	@RequestMapping(value = "/room", method = RequestMethod.PUT)
	public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room room) throws ApplicationException {
		logger.info("updating room...");
		Room updatedRoom = roomService.getRoomsById(room.getRoomId());

		try {

			updatedRoom = roomService.updateRoom(room);
			logger.info("Room updated succeccfully with id : " + updatedRoom);
			return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
		} catch (ApplicationException e) {
			logger.debug("Updating room failed...");
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ApplicationException
	 * 
	 *             Delete a room
	 */
	@RequestMapping(value = "/room/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Response> deleteRoom(@PathVariable("id") long id) throws ApplicationException {
		try {
			logger.info("Room id to remove " + id);

			roomService.deleteRoom(id);
			logger.info("Room deleted succeccfully with id : " + id);
			return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "Room has been deleted"), HttpStatus.OK);
		} catch (ApplicationException e) {
			logger.error("Room cannot be deleted with id : " + id);
			return new ResponseEntity<>(
					new Response(HttpStatus.NOT_FOUND.value(), "Rooms cannot be deleted with id: " + id),
					HttpStatus.NOT_FOUND);
		}
	}

}
