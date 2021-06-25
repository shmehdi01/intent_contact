
import 'dart:async';

import 'package:flutter/services.dart';

class IntentContact {
  static const MethodChannel _channel =
      const MethodChannel('intent_contact');

  static Future<String> get platformContactPage async {
    final String version = await _channel.invokeMethod('openContactPage');
    return version;
  }
}
