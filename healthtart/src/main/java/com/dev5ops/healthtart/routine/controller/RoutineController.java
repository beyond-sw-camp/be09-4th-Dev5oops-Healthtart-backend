package com.dev5ops.healthtart.routine.controller;

import com.dev5ops.healthtart.routine.domain.dto.RoutineDTO;
import com.dev5ops.healthtart.routine.domain.vo.*;
import com.dev5ops.healthtart.routine.service.RoutineServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineServiceImpl routineService;
    private final ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "운동 루틴 전체 조회")
    public ResponseEntity<List<ResponseFindRoutineVO>> getAllRoutines() {
        List<ResponseFindRoutineVO> routines = routineService.getRoutines();
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }

    @GetMapping("/{routineCode}")
    @Operation(summary = "운동 루틴 단일 조회")
    public ResponseEntity<ResponseFindRoutineVO> getRoutineByCode(@PathVariable Long routineCode) {
        ResponseFindRoutineVO routine = routineService.findRoutineByCode(routineCode);
        return new ResponseEntity<>(routine, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "운동 루틴 등록")
    public ResponseEntity<ResponseInsertRoutineVO> registerRoutine(@RequestBody RequestInsertRoutineVO requestInsertRoutineVO) {
        RoutineDTO routineDTO = modelMapper.map(requestInsertRoutineVO, RoutineDTO.class);
        ResponseInsertRoutineVO response = routineService.registerRoutine(routineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
