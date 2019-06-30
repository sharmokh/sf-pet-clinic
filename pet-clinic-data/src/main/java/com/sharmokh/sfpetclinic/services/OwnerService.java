package com.sharmokh.sfpetclinic.services;

import com.sharmokh.sfpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
