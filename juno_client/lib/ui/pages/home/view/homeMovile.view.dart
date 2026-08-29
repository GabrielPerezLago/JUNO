import 'package:flutter/material.dart';
import 'package:juno_client/domain/entity/SESSION.dart';

class HomeMovileView extends StatefulWidget {

  @override
  State<StatefulWidget> createState() => _HomeMovileState();

}

class _HomeMovileState extends State {
  final SESSION _session = SESSION.instance;

  @override
  Widget build(BuildContext context) => Scaffold(
    body: Text(
      _session.nombre!.toUpperCase(),
      style: TextStyle(
        fontSize: 100
      ),    
    ),
  );

}