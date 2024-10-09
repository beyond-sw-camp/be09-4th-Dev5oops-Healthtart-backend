package com.dev5ops.healthtart.gym.service;

import com.dev5ops.healthtart.common.exception.CommonException;
import com.dev5ops.healthtart.common.exception.StatusEnum;
import com.dev5ops.healthtart.gym.aggregate.Gym;
import com.dev5ops.healthtart.gym.aggregate.vo.request.RequestEditGymVO;
import com.dev5ops.healthtart.gym.dto.GymDTO;
import com.dev5ops.healthtart.gym.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service("gymService")
public class GymService {
    private final GymRepository gymRepository;
    private final ModelMapper modelMapper;

    public GymDTO registerGym(GymDTO gymDTO) {
        Gym gym = Gym.builder()
                .gymName(gymDTO.getGymName())
                .address(gymDTO.getAddress())
                .businessNumber(gymDTO.getBusinessNumber())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .equipmentPerGyms(new ArrayList<>())
                .build();

        if (gymRepository.findByBusinessNumber(gym.getBusinessNumber()).isPresent()) throw new CommonException(StatusEnum.GYM_DUPLICATE);

        gym = gymRepository.save(gym);
        return modelMapper.map(gym, GymDTO.class);
    }

    public GymDTO editGym(Long gymCode, RequestEditGymVO request) {
        Gym gym = gymRepository.findById(gymCode).orElseThrow(() -> new CommonException(StatusEnum.GYM_NOT_FOUND));

        gym.setGymName(request.getGymName());
        gym.setAddress(request.getAddress());
        gym.setBusinessNumber(request.getBusinessNumber());
        gym.setUpdatedAt(LocalDateTime.now());
        gym.setEquipmentPerGyms(request.getEquipmentPerGyms());

        gym = gymRepository.save(gym);

        return modelMapper.map(gym, GymDTO.class);
    }

    public void deleteGym(Long gymCode) {
        Gym gym = gymRepository.findById(gymCode).orElseThrow(() -> new CommonException(StatusEnum.GYM_NOT_FOUND));

        gymRepository.delete(gym);
    }

    public GymDTO findGymByGymCode(Long gymCode) {
        Gym gym = gymRepository.findById(gymCode).orElseThrow(() -> new CommonException(StatusEnum.GYM_NOT_FOUND));

        return modelMapper.map(gym, GymDTO.class);
    }

    public List<GymDTO> findAllGym() {
        List<Gym> gymList = gymRepository.findAll();

        return gymList.stream()
                .map(gym -> modelMapper.map(gym, GymDTO.class))
                .collect(Collectors.toList());
    }
}
