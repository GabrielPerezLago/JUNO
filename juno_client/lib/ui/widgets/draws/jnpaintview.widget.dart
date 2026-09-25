import 'package:flutter/material.dart';
import 'package:juno_client/config/app/app.tools.dart';

class JnPaintView extends StatelessWidget {
  
  const JnPaintView({super.key});


  @override
  Widget build(BuildContext context) => CustomPaint(
    size: Size.infinite,
    painter: _JnBoxPainter(context: context),

  );
}


class _JnBoxPainter extends CustomPainter {


  final BuildContext context;

  _JnBoxPainter({
    required this.context
  });


  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint();
    final path = Path();

    final w = size.width;
    final h = size.height;

    var sz = w/h;
    print(sz);
    paint.color = junoColorScheme(context).primaryFixed;

    paint.strokeWidth = 50;
    paint.style = PaintingStyle.fill;


    
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) => true;
}