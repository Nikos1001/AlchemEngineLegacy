
from engine import CollisionBox
import math

class Box:

	def __init__(self, x, y):
		self.box = CollisionBox(x, y, 1, 1)

	def draw(self):
		ctx.spr(tex, self.box.x, self.box.y, 1, 1, 0, shad)

class Paddle:
	
	def __init__(self):
		w = 4
		x = (ctx.width() - w) / 2
		y = ctx.height() - 2
		h = 0.5

		# the box will not only be used for the collisions,
		# but also to store position
		self.box = CollisionBox(x, y, w, h)
		self.speed = 15

	def draw(self):
		ctx.spr(tex, self.box.x, self.box.y, self.box.w, self.box.h, 0, shad)

	def update(self, dt):

		# get player input
		dir = 0
		if ctx.key('A'):
			dir -= 1
		if ctx.key('D'):
			dir += 1

		# update x position and collide with the screen boundaries
		self.box.x += dir * self.speed * dt
		self.box.x = max(0, self.box.x)
		self.box.x = min(ctx.width() - self.box.w, self.box.x)

class Ball:

	def __init__(self):
		w = 0.5
		x = (ctx.width() - w) / 2
		y = ctx.height() - 3
		h = 0.5

		self.box = CollisionBox(x, y, w, h)
		self.speed = 5
		self.vx = self.speed
		self.vy = self.speed

	def draw(self):
		ctx.spr(tex, self.box.x, self.box.y, self.box.w, self.box.h, 0, shad)

	def update(self, dt):

		self.box.x += self.vx * dt
		self.box.y += self.vy * dt

		if self.box.x < 0:
			self.vx *= -1
			self.box.x = 0
		if self.box.x > ctx.width() - self.box.w:
			self.vx *= -1
			self.box.x = ctx.width() - self.box.w
		if self.box.y < 0:
			self.vy *= -1
			self.box.y = 0

	def collide(self, other):
		col = self.box.collideWith(other)
		if col.collided:
			if col.horizontal:
				self.vx *= -1
			if col.vertical:
				self.vy *= -1
			return True
		return False

	def collidePaddle(self, paddle):
		col = self.box.collideWith(paddle.box)
		if col.collided:
			if col.vertical:
				vy = -self.speed
				if self.vx > 0:
					vx = self.speed
				else:
					vx = -self.speed

				xMult = 1 + 2.5 * abs((paddle.box.x + paddle.box.w / 2) - (self.box.x + self.box.w / 2)) / paddle.box.w
				vx *= xMult

				self.vx = vx
				self.vy = vy

			if col.horizontal:
				self.vx *= -1


tex = None
shad = None
paddle = None
ball = None
boxes = []
lives = 0

def start():
	startLevel()

def startLevel():
	global tex, paddle, ball, boxes, lives, shad
	tex = ctx.loadSprite('-blank')
	shad = ctx.loadShader('rainbow')
	paddle = Paddle()
	ball = Ball()

	if lives > 0:
		pass
	else:
		boxes = []
		for x in range(0, int(ctx.width())):
			for y in range(2, 7):
				box = Box(x, y)
				boxes.append(box)
		lives = 3

def update(dt):
	global lives
	paddle.update(dt)
	ball.update(dt)
	ball.collidePaddle(paddle)

	for box in boxes:
		if ball.collide(box.box):
			boxes.remove(box)
			break

	if ball.box.y > ctx.height():
		lives -= 1
		startLevel()

	ctx.clr(0.075, 0.075, 0.1)
	paddle.draw()
	ball.draw()

	for box in boxes:
		box.draw()
