package com.task_tracker.users_api.service;

import com.task_tracker.users_api.dto.UserCreateDto;
import com.task_tracker.users_api.dto.UserDto;
import com.task_tracker.users_api.dto.UserEnvelopDto;

/**
 * Интрерфейс, предоставляющий API для обработки пользователей
 */
 public interface UserService {

    /**
     * Получение пользователей по фильтру
     * @param page номер страницы
     * @param size кол-во пользователей
     * @param sort поле для сортировки
     * @return envelop пользователя
     */
    UserEnvelopDto getUsers(int page, int size, String sort);

   /**
    * Получение пользователя по ID
    * @param id ID пользователя
    * @return Dto пользователя
    */
    UserDto findById(Long id);

    /**
     * Создание пользователя
     * @param userDto CreateDto пользователя
     * @return Dto пользователя
     */
    UserDto create(UserCreateDto userDto);

    /**
     * Удаление пользователя
     * @param id ID пользователя
     */
    void delete(Long id) ;
}
