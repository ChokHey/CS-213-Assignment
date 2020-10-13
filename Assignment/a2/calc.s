.pos 0x100
            ld  $0, r0
            ld  $a, r1
            ld  (r1, r0, 4), r2
            inc r0
            st  r2, (r1, r0, 4)
.pos 0x1000
a:          .long 1
            .long 2
            .long 3
            .long 4
            .long 5