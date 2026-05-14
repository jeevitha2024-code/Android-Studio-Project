package com.example.aksharadeepatutor.ui.components

import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry

@Composable
fun RadarChartView(
    mathScore: Float,
    scienceScore: Float,
    socialScore: Float,
    modifier: Modifier = Modifier
) {

    AndroidView(
        modifier = modifier,
        factory = { context ->

            val radarChart = RadarChart(context)

            radarChart.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                900
            )

            val entries = listOf(
                RadarEntry(mathScore),
                RadarEntry(scienceScore),
                RadarEntry(socialScore)
            )

            val dataSet = RadarDataSet(entries, "Subject Mastery").apply {
                color = Color.rgb(0, 150, 136)
                fillColor = Color.rgb(0, 150, 136)
                setDrawFilled(true)
                fillAlpha = 180
                lineWidth = 2f
                valueTextSize = 14f
                valueTextColor = Color.BLACK
            }

            val radarData = RadarData(dataSet)

            radarChart.data = radarData

            radarChart.description.isEnabled = false

            radarChart.webLineWidth = 2f
            radarChart.webColor = Color.LTGRAY

            radarChart.webLineWidthInner = 2f
            radarChart.webColorInner = Color.LTGRAY

            radarChart.yAxis.apply {
                axisMinimum = 0f
                axisMaximum = 100f
                setLabelCount(5, true)
                textSize = 12f
            }

            radarChart.xAxis.apply {
                valueFormatter =
                    object : com.github.mikephil.charting.formatter.ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return when (value.toInt()) {
                                0 -> "Math"
                                1 -> "Science"
                                2 -> "Social"
                                else -> ""
                            }
                        }
                    }

                position = XAxis.XAxisPosition.BOTTOM_INSIDE
                textSize = 14f
            }

            radarChart.legend.textSize = 14f

            radarChart.animateXY(1500, 1500)

            radarChart.invalidate()

            radarChart
        }
    )
}