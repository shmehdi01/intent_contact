import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:intent_contact/intent_contact.dart';

void main() {
  const MethodChannel channel = MethodChannel('intent_contact');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await IntentContact.platformContactPage, '42');
  });
}
