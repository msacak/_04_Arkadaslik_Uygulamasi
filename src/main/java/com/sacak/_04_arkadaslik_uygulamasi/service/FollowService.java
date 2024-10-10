package com.sacak._04_arkadaslik_uygulamasi.service;


import com.sacak._04_arkadaslik_uygulamasi.dto.request.AddFollowRequestDto;
import com.sacak._04_arkadaslik_uygulamasi.entity.Follow;
import com.sacak._04_arkadaslik_uygulamasi.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    public void addFollow(AddFollowRequestDto dto) {
        Follow follow = Follow.builder().userId(dto.getUserId())
                .followId(dto.getFollowingId()).build();
        followRepository.save(follow);
    }
}
