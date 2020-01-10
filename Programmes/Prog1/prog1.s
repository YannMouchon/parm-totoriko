main:
	sub	sp, sp, #16
	add	r7, sp, #0
	movs	r3, #0
	str	r3, [r7, #12]
	movs	r3, #5
	str	r3, [r7, #8]
	movs	r3, #0
	str	r3, [r7, #4]
	b	.L2
.L3:
	ldr	r3, [r7, #12]
	adds	r3, r3, #1
	str	r3, [r7, #12]
.L2:
	ldr	r2, [r7, #12]
	ldr	r3, [r7, #8]
	cmp	r2, r3
	blt	.L3
	movs	r3, #100
	str	r3, [r7, #4]
	movs	r3, #0
	movs	r0, r3
	mov	sp, r7
	add	sp, sp, #16
