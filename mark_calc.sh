#!/usr/bin/env ruby

numerator = 285 - ARGV[0].to_i

functional = (numerator.to_f / 285) * 55

marks = (10 + 15 + functional.to_i)

puts(marks)

