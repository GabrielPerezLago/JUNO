import 'dart:isolate';

import 'package:flutter/material.dart';
import 'package:juno_client/domain/seession/SESSION.dart';

class HomeMovileView extends StatefulWidget {
  const HomeMovileView({super.key});

  @override
  State<StatefulWidget> createState() => _HomeMovileState();
}

class _HomeMovileState extends State<HomeMovileView> {
  final SESSION session = SESSION.instance;

  @override
  Widget build(BuildContext context) =>
      Scaffold(body: Text(session.nombre!, style: TextStyle(fontSize: 100)));
}
