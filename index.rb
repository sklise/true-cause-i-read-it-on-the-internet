require 'bundler'
Bundler.require

configure do |c|
  set :database, ENV['DATABASE_URL'] || 'sqlite://development.sqlite'
  # Turn on sessions. Makes the `session` hash available to routes and views.
  # enable :sessions
  set :root, File.dirname(__FILE__)
  set :views, Proc.new{ File.join(root, "views")}
end

require './models'
require './helpers'
require './routes'