package io.member.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.member.Member;
import io.member.MemberRepository;

import static java.nio.charset.StandardCharsets.*;

public class FileMemberRepository implements MemberRepository{

    private static final String FILE_PATH = "temp/member-text.dat";
    private static final String DELEMITER = ",";

    @Override
    public void add(Member member) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, UTF_8, true));) {
            bw.write(member.getId() + DELEMITER + member.getName() + DELEMITER + member.getAge());
            bw.newLine(); // 저장 후 줄 추가
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH, UTF_8));) {
            String line;
            while((line = br.readLine()) != null) {
                String[] member = line.split(DELEMITER);
                members.add(new Member(member[0], member[1], Integer.valueOf(member[2])));
            }
            return members;
        } catch(FileNotFoundException e) {
            return new ArrayList<>(); // 첫 등록 이전 조회 시, 빈 리스트 전달
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
