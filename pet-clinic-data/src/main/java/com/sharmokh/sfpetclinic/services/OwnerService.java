package com.sharmokh.sfpetclinic.services;

import com.sharmokh.sfpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
    List<Owner> findAllByLastName(String lastName);

}
