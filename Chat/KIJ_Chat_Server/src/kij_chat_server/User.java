/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kij_chat_server;

import java.util.ArrayList;

/**
 *
 * @author santen-suru
 */
public class User {
    // User-Password list
    private ArrayList<Pair<String,String>> _userlist = new ArrayList<>();
    
    User() {
        _userlist.add(new Pair("Admin", "e3afed0047b08059d0fada10f400c1e5"));
        _userlist.add(new Pair("Andi", "5f1d0630d2deaa9f541131a857240ac0"));
        _userlist.add(new Pair("Budi", "5894c82cc2aeb6560140a81694f99051"));
        _userlist.add(new Pair("Rudi", "06c572878f11d3b55bcc9fcb9476f26e"));
        _userlist.add(new Pair("Luci", "ccf749dcf7aa6920edb4f9dac925f0a0"));
    }
    
    public ArrayList<Pair<String,String>> getUserList() {
        return _userlist;
    }
}
