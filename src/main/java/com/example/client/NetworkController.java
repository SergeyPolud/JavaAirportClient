package com.example.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkController
{
    public static Socket connection;
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;
}
