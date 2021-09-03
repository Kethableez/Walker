package com.kethableez.walkerapi.Walk.Controller;

import java.util.List;

import com.kethableez.walkerapi.User.Service.UserService;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;
import com.kethableez.walkerapi.Utility.Response.MessageResponse;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkInfo;
import com.kethableez.walkerapi.Walk.Model.Request.WalkRequest;
import com.kethableez.walkerapi.Walk.Service.WalkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/walk")
@RequiredArgsConstructor
public class WalkController {

    @Autowired
    private final WalkService walkService;

    @Autowired
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<WalkInfo>> getWalks() {
        List<WalkInfo> walks = walkService.getAllAvailableWalkInfo();
        return new ResponseEntity<>(walks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalkCard> getWalkCard(@PathVariable("id") String walkId) {
        WalkCard walk = this.walkService.createCard(this.walkService.getWalkFromId(walkId));
        return new ResponseEntity<>(walk, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> createWalk(@RequestBody WalkRequest request, UsernamePasswordAuthenticationToken token) {
        ActionResponse response = walkService.createWalk(token, request);

        if (response.isSuccess())
            return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else
            return ResponseEntity.badRequest().body(response.getMessage());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> deleteWalk(@PathVariable("id") String walkId, UsernamePasswordAuthenticationToken token) {
        ActionResponse response = walkService.deleteWalk(walkId, userService.getIdFromToken(token));

        if (response.isSuccess())
            return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else
            return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PostMapping("/enroll/{id}")
    @PreAuthorize("hasRole('SITTER')")
    public ResponseEntity<?> enroll(@PathVariable("id") String walkId, UsernamePasswordAuthenticationToken token) {
        ActionResponse response = walkService.walkEnroll(token, walkId);

        if (response.isSuccess())
            return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else
            return ResponseEntity.badRequest().body(response.getMessage());
    }

    @PostMapping("/disenroll/{id}")
    @PreAuthorize("hasRole('SITTER')")
    public ResponseEntity<?> disenroll(@PathVariable("id") String walkId, UsernamePasswordAuthenticationToken token) {
        ActionResponse response = walkService.walkDisenroll(token, walkId);

        if (response.isSuccess())
            return ResponseEntity.ok(new MessageResponse(response.getMessage()));
        else
            return ResponseEntity.badRequest().body(response.getMessage());
    }
}
