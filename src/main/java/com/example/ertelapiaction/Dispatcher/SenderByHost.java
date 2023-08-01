package com.example.ertelapiaction.Dispatcher;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.stream.Stream;

@Data
@Component
public class SenderByHost {

    @Value("${actions.users}")
    private List<String> users;
    @Value("${actions.domofone.openmaindoor}")
    private String[] domofoneOpenmaindoor;

    public String open(String ip){
        isReachable(ip);
        String answer = "";
        String error = "";
        for(String user : users){
            for (String uri : domofoneOpenmaindoor) {
                try {
                    answer = send(ip, uri, user);
                } catch (Exception e) {
                    error = error + "; " + e.getMessage();
                }
            }
        }
        if(answer.isEmpty()) throw new RuntimeException(error);
        return answer;
    }

    private String send(String ip, String uri, String user) throws RuntimeException{
        Stream<String> stream = WebClient.builder()
                .baseUrl("http://" + ip)
                .build()
                .get()
                .uri(uri + user)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToFlux(String.class).toStream();
        return stream.reduce(String::concat).orElse("");
    }

    private void isReachable(String ip){
        try {
            int i=0;
            while (!InetAddress.getByName(ip).isReachable(300) && i<4){
                throw new RuntimeException(ip + " address is not reachable");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
