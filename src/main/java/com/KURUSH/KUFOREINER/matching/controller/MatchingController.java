package com.KURUSH.KUFOREINER.matching.controller;

import com.KURUSH.KUFOREINER.global.response.HttpResponse;
import com.KURUSH.KUFOREINER.matching.dto.MatchingAddRequest;
import com.KURUSH.KUFOREINER.matching.dto.MatchingReadResponse;
import com.KURUSH.KUFOREINER.matching.service.MatchingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matchings")
@Slf4j
public class MatchingController {
    private final MatchingService matchingService;

    @GetMapping
    public HttpResponse<List<MatchingReadResponse>> getMyMatchingList() {
        List<MatchingReadResponse> matchings = matchingService.getMyMatchingList();
        return HttpResponse.okBuild(matchings);
    }

    @PostMapping("/add")
    public HttpResponse<String> addMatching(@RequestBody MatchingAddRequest request) {
        matchingService.addMatching(request);
        return HttpResponse.okBuild("Matching added successfully");
    }
}
