import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:intent_contact/intent_contact.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String contact = '';

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> loadContactPage() async {
    String data;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      data = await IntentContact.platformContactPage;
    } on PlatformException {
      data = 'Failed to get contact.';
    }

    if (!mounted) return;

    setState(() {
      contact = data;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Contact Example App'),
        ),
        body: Column(
          children: [
            TextButton(onPressed: () {
                loadContactPage();
            }, child: Text("Get Contacts")),
            Center(
              child: Text('Contact: $contact\n'),
            ),
          ],
        ),
      ),
    );
  }
}
