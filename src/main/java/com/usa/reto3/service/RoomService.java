package com.usa.reto3.service;

import com.usa.reto3.model.Room;
import com.usa.reto3.repository.RoomRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author nancy
 */
@Service
public class RoomService {

    @Autowired

    private RoomRepository metodosCrud;

    public List<Room> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Room> getRoom(int id) {
        return metodosCrud.getRoom(id);
    }

    public Room save(Room room) {
        if (room.getId() == null) {
            return metodosCrud.save(room);

        } else {

            Optional<Room> evt = metodosCrud.getRoom(room.getId());
            if (evt ==null) {
                return metodosCrud.save(room);

            } else {
                return room;

            }

        }

    }
public Room update(Room room){
        if(room.getId()!=null){
            Optional<Room> tmpRoom =metodosCrud.getRoom(room.getId());
            if(!tmpRoom.isEmpty()){
                if(room.getName()!=null){
                    tmpRoom.get().setName(room.getName());
                }
                if(room.getStars()!=null){
                    tmpRoom.get().setStars(room.getStars()); ;
                }
                if(room.getHotel() !=null){
                    tmpRoom.get().setHotel(room.getHotel());
                }
                if(room.getDescription()!=null){
                    tmpRoom.get().setDescription(room.getDescription());
                }
                if(room.getCategory()!=null){
                    tmpRoom.get().setCategory(room.getCategory());
                }
                metodosCrud.save(tmpRoom.get());
                return tmpRoom.get();
            }else{
                return room;
            }
        }else{
            return room;
        }
    }


    public boolean deleteRoom(int roomId) {
        Boolean d = getRoom(roomId).map(room -> {
            metodosCrud.delete(room);
            return true;
        }).orElse(false);
        return d;
    }
}