package com.example.whattodo;

public interface FragmentCommunicator {

    public void passStringDataToFragment(String key, String someValue);
    public void passIntDataToFragment(String key, int someValue);
}
