package org.tms.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tms.finalproject.entity.Dialog;
import org.tms.finalproject.entity.embeddable.DialogId;

public interface DialogRepository extends JpaRepository<Dialog, DialogId> {
    boolean existsById(DialogId dialogId);
}
