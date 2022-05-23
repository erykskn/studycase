package com.ery.getir.studycase.responses;

import com.ery.getir.studycase.dtos.MontlyStaticticDto;

import java.util.List;

public class MontlyStaticticReponse {

    List<MontlyStaticticDto> montlyStaticticDtoList;

    public List<MontlyStaticticDto> getMontlyStaticticDtoList() {
        return montlyStaticticDtoList;
    }

    public void setMontlyStaticticDtoList(List<MontlyStaticticDto> montlyStaticticDtoList) {
        this.montlyStaticticDtoList = montlyStaticticDtoList;
    }
}
